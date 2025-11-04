package com.parkingon.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class ResourceController {

    @GetMapping("/favicon.ico")
    public ResponseEntity<byte[]> getFavicon() {
        // 빈 favicon
        byte[] emptyFavicon = new byte[0];

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("image/x-icon"));
        headers.setCacheControl("public, max-age=31536000");

        return new ResponseEntity<>(emptyFavicon, headers, HttpStatus.OK);
    }

    @GetMapping("/images/logo.png")
    public ResponseEntity<byte[]> getLogo() {
        // 1x1 투명 PNG (Base64)
        String transparentPng = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNk+M9QDwADhgGAWjR9awAAAABJRU5ErkJggg==";
        byte[] imageBytes = Base64.getDecoder().decode(transparentPng);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setCacheControl("public, max-age=31536000");

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/images/family-illustration.png")
    public ResponseEntity<byte[]> getFamilyIllustration() {
        // 1x1 투명 PNG (Base64)
        String transparentPng = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNk+M9QDwADhgGAWjR9awAAAABJRU5ErkJggg==";
        byte[] imageBytes = Base64.getDecoder().decode(transparentPng);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setCacheControl("public, max-age=31536000");

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
