package com.backend.NOV.Services;

import com.backend.NOV.Entities.User;
import com.backend.NOV.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public ResponseEntity<?> checkCode(String code, String email){
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isEmpty()) return ResponseEntity.status(500).body("user not found");

        User user = optionalUser.get();
        if (!code.equals(user.getCode())) return  ResponseEntity.status(500).body("wrong code");
        user.setAuthorized(true);
        userRepository.save(user);
        return ResponseEntity.ok("success");
    }

    public void sendCode(User user) {
        String randomNumber = getRandomNumberString();

        user.setCode(randomNumber);
        userRepository.save(user);
        emailService.sendEmail(user.getEmail(), "Код верифифкации", "Ваш код: " + user.getCode());
    }

    private String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }
}
