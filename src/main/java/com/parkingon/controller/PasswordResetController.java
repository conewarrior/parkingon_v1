package com.parkingon.controller;

import com.parkingon.entity.PasswordResetToken;
import com.parkingon.entity.User;
import com.parkingon.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/password-reset")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    /**
     * 비밀번호 재설정 요청 페이지
     */
    @GetMapping("/request")
    public String showResetRequestPage() {
        return "password-reset-request";
    }

    /**
     * 비밀번호 재설정 토큰 생성
     */
    @PostMapping("/request")
    public String requestPasswordReset(@RequestParam String username, RedirectAttributes redirectAttributes) {
        try {
            PasswordResetToken token = passwordResetService.createResetToken(username);

            // 실제 운영환경에서는 이메일로 링크 전송
            // 여기서는 관리자가 직접 확인할 수 있도록 메시지에 포함
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("message", "비밀번호 재설정 요청이 접수되었습니다. 관리자가 승인 후 재설정이 가능합니다.");
            redirectAttributes.addFlashAttribute("token", token.getToken());

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", "존재하지 않는 사용자입니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", "처리 중 오류가 발생했습니다.");
        }

        return "redirect:/password-reset/request";
    }

    /**
     * 비밀번호 재설정 페이지 (토큰 검증)
     */
    @GetMapping("/reset")
    public String showResetPage(@RequestParam String token, Model model, RedirectAttributes redirectAttributes) {
        // 토큰 유효성 검증
        if (!passwordResetService.validateToken(token)) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", "유효하지 않거나 만료된 토큰입니다.");
            return "redirect:/login";
        }

        Optional<User> userOpt = passwordResetService.getUserByToken(token);
        if (userOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", "사용자를 찾을 수 없습니다.");
            return "redirect:/login";
        }

        model.addAttribute("token", token);
        model.addAttribute("username", userOpt.get().getUsername());
        return "password-reset";
    }

    /**
     * 비밀번호 재설정 처리
     */
    @PostMapping("/reset")
    public String resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttributes) {

        // 비밀번호 확인
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", "비밀번호가 일치하지 않습니다.");
            redirectAttributes.addAttribute("token", token);
            return "redirect:/password-reset/reset";
        }

        // 비밀번호 복잡성 검증 (최소 8자)
        if (newPassword.length() < 8) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", "비밀번호는 최소 8자 이상이어야 합니다.");
            redirectAttributes.addAttribute("token", token);
            return "redirect:/password-reset/reset";
        }

        // 비밀번호 재설정
        boolean success = passwordResetService.resetPassword(token, newPassword);

        if (success) {
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("message", "비밀번호가 성공적으로 변경되었습니다. 새 비밀번호로 로그인해주세요.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", "비밀번호 재설정에 실패했습니다. 토큰이 유효하지 않습니다.");
            return "redirect:/login";
        }
    }
}
