package com.itstudent.controller.public_api;

import com.itstudent.entities.payload.LoginForm;
import com.itstudent.entities.payload.RegisterForm;
import com.itstudent.security.JWTService;
import com.itstudent.service.UserService;
import com.itstudent.service_impl.GoogleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/public/user")
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;
    private final GoogleService googleService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm form) {
        try {
            boolean rs = userService.login(form);
            if (rs) {
                return ResponseEntity
                        .ok(jwtService.generateToken(form.getUsername()));
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e);
        }
    }



    // to do
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterForm form) {
        try {
            boolean rs = userService.register(form);
            if (rs) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e);
        }
    }
}
