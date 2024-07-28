package com.ilouse.geekstoreV2.Controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@RequestMapping("/admin")
@Controller
public class AdminController {
    @GetMapping
    public ResponseEntity<String> goadmin(HttpServletResponse response) throws Exception {
        return ResponseEntity.badRequest().body("Badrequest");
    }
}
