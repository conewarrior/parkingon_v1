package com.parkingon.controller;

import com.parkingon.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final CommonCodeRepository commonCodeRepository;
    private final SystemConfigRepository systemConfigRepository;
    private final NotifyRepository notifyRepository;

    @GetMapping
    public String systemIndex() {
        return "redirect:/system/user-manage";
    }

    @GetMapping("/user-manage")
    public String userManage(HttpSession session, Model model) {
        setSessionInfo(session);

        // 아파트 목록
        model.addAttribute("apartments", apartmentRepository.findAll());

        // 사용자 목록
        model.addAttribute("users", userRepository.findAll());

        return "system/user-manage";
    }

    @GetMapping("/code-manage")
    public String codeManage(HttpSession session, Model model) {
        setSessionInfo(session);

        // 공통코드 목록
        model.addAttribute("codes", commonCodeRepository.findByGroupCodeOrderBySortOrder("CUST_TYPE"));

        return "system/code-manage";
    }

    @GetMapping("/config")
    public String config(HttpSession session, Model model) {
        setSessionInfo(session);

        // 시스템 설정
        model.addAttribute("configs", systemConfigRepository.findAll());

        return "system/config";
    }

    @GetMapping("/notify-manage")
    public String notifyManage(HttpSession session, Model model) {
        setSessionInfo(session);

        // 아파트 목록
        model.addAttribute("apartments", apartmentRepository.findAll());

        // 공지사항 목록
        model.addAttribute("notifies", notifyRepository.findAll());

        return "system/notify-manage";
    }

    private void setSessionInfo(HttpSession session) {
        if (session.getAttribute("username") == null) {
            session.setAttribute("username", "관리자");
            session.setAttribute("loginTime",
                java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
}
