package pe.cibertec.gestion_docente.presentation.controller;

import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/test")
public class TestController {
    @GetMapping public String hello(){ return "OK protegido"; }
}
