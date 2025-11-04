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
@RequestMapping("/apt")
@RequiredArgsConstructor
public class AptController {

    private final ApartmentRepository apartmentRepository;
    private final LprDeviceRepository lprDeviceRepository;
    private final CarRepository carRepository;

    @GetMapping
    public String aptIndex() {
        return "redirect:/apt/manage";
    }

    @GetMapping("/manage")
    public String aptManage(HttpSession session, Model model) {
        setSessionInfo(session);

        // 아파트 목록 조회
        model.addAttribute("apartments", apartmentRepository.findAll());

        return "apt/apt-manage";
    }

    @GetMapping("/lpr-device")
    public String lprDevice(HttpSession session, Model model) {
        setSessionInfo(session);

        // 아파트 목록
        model.addAttribute("apartments", apartmentRepository.findAll());

        // LPR 장비 목록
        var apartments = apartmentRepository.findAll();
        if (!apartments.isEmpty()) {
            model.addAttribute("devices", lprDeviceRepository.findByApartmentId(apartments.get(0).getId()));
        }

        return "apt/lpr-device";
    }

    @GetMapping("/car-manage")
    public String carManage(HttpSession session, Model model) {
        setSessionInfo(session);

        // 아파트 목록
        model.addAttribute("apartments", apartmentRepository.findAll());

        // 차량 목록
        model.addAttribute("cars", carRepository.findAll());

        return "apt/car-manage";
    }

    private void setSessionInfo(HttpSession session) {
        if (session.getAttribute("username") == null) {
            session.setAttribute("username", "관리자");
            session.setAttribute("loginTime",
                java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
}
