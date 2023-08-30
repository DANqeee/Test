package com.backend.NOV.Controllers;

import com.backend.NOV.Entities.User;
import com.backend.NOV.Repositories.UserRepository;
import com.backend.NOV.Services.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MainController {

    private final UserRepository userRepository;
    private final CodeService codeService;

    @PostMapping("/email-code")
    public ResponseEntity<?> sendEmailWithCode(@RequestBody User user){
        User createdUser = userRepository.save(user);
        codeService.sendCode(createdUser);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/check-code")
    public ResponseEntity<?> checkCode(@RequestBody User user) {
        return codeService.checkCode(user.getCode(), user.getEmail());
    }
}