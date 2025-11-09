package com.parkingon.controller;

import com.parkingon.entity.Notify;
import com.parkingon.repository.NotifyRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NotifyRepository notifyRepository;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        setSessionInfo(session, model);

        // 대시보드 데이터 (샘플)
        model.addAttribute("totalSites", 123);
        model.addAttribute("normalSites", 120);
        model.addAttribute("errorSites", 3);

        // 활성화된 공지사항 가져오기
        List<Notify> activeNotifies = notifyRepository.findByIsActiveTrueOrderBySendDateDesc();
        if (!activeNotifies.isEmpty()) {
            model.addAttribute("notifies", activeNotifies);
            model.addAttribute("currentNotify", activeNotifies.get(0));
            model.addAttribute("showPopup", true);
        }

        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // "/" 경로로 리다이렉트
        return "redirect:/";
    }

    @GetMapping("/dashboard-worker")
    public String dashboardWorker(HttpSession session, Model model) {
        setSessionInfo(session, model);

        // 더미 아파트 데이터 생성
        java.util.List<java.util.Map<String, Object>> apartments = new java.util.ArrayList<>();
        String[] aptNames = {"삼성1단지", "래미안2차", "e편한세상", "힐스테이트", "자이아파트",
                            "푸르지오", "더샵", "롯데캐슬", "SK뷰", "호반베르디움",
                            "동아아파트", "현대아파트", "대우아파트", "LG아파트", "GS아파트"};

        for (int i = 0; i < aptNames.length; i++) {
            java.util.Map<String, Object> apt = new java.util.HashMap<>();
            apt.put("id", (long)(i + 1));
            apt.put("code", String.format("A%07d", i + 1));
            apt.put("name", aptNames[i]);
            apartments.add(apt);
        }

        model.addAttribute("apartments", apartments);
        return "dashboard-worker";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    private void setSessionInfo(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            session.setAttribute("username", "관리자");
            session.setAttribute("loginTime",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
}
