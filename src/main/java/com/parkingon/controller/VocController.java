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
@RequestMapping("/voc")
@RequiredArgsConstructor
public class VocController {

    private final ApartmentRepository apartmentRepository;
    private final VocRepository vocRepository;
    private final InOutHistoryRepository inOutHistoryRepository;
    private final ControlHistoryRepository controlHistoryRepository;

    @GetMapping
    public String vocIndex() {
        return "redirect:/voc/list";
    }

    @GetMapping("/list")
    public String vocList(HttpSession session, Model model) {
        setSessionInfo(session);

        // 아파트 목록
        model.addAttribute("apartments", apartmentRepository.findAll());

        // VOC 이력
        model.addAttribute("vocs", vocRepository.findAll());

        return "voc/voc-list";
    }

    @GetMapping("/inout-car")
    public String inoutCar(HttpSession session, Model model) {
        setSessionInfo(session);

        // 아파트 목록
        model.addAttribute("apartments", apartmentRepository.findAll());

        // 입출차 이력
        var apartments = apartmentRepository.findAll();
        if (!apartments.isEmpty()) {
            model.addAttribute("histories",
                inOutHistoryRepository.findByApartmentIdOrderByInoutTimeDesc(apartments.get(0).getId()));
        }

        return "voc/inout-car";
    }

    @GetMapping("/control-history")
    public String controlHistory(HttpSession session, Model model) {
        setSessionInfo(session);

        // 아파트 목록
        model.addAttribute("apartments", apartmentRepository.findAll());

        // 제어 이력
        var apartments = apartmentRepository.findAll();
        if (!apartments.isEmpty()) {
            model.addAttribute("controls",
                controlHistoryRepository.findByApartmentIdOrderByControlTimeDesc(apartments.get(0).getId()));
        }

        return "voc/control-history";
    }

    private void setSessionInfo(HttpSession session) {
        if (session.getAttribute("username") == null) {
            session.setAttribute("username", "관리자");
            session.setAttribute("loginTime",
                java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
}
