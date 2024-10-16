package com.hunter.user.controller;

import com.hunter.exception.DuplicatedEmailException;
import com.hunter.exception.NoRegisteredArgumentsException;
import com.hunter.user.dto.request.UserSignUpRequestDTO;
import com.hunter.user.dto.response.UserSignUpResponseDTO;
import com.hunter.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:3001"})
public class UserController {
    private final UserService userService;

    //회원가입 요청 처리
    @PostMapping
    public ResponseEntity<?> signUp(
            @Validated @RequestBody UserSignUpRequestDTO dto,
            BindingResult result
    ) {
        log.info("Sign up request: {}", dto);

        if(result.hasErrors()) {
            log.warn(result.toString());
            return ResponseEntity
                    .badRequest()
                    .body(result.getAllErrors());
        }
        try {
            UserSignUpResponseDTO responseDTO =  userService.create(dto);
            return ResponseEntity.ok(responseDTO);
        }catch (NoRegisteredArgumentsException e) {
            log.warn("필수 가입 정보를 전달받지 못했습니다!!");
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (DuplicatedEmailException e){
            log.warn("이메일이 중복되었습니다.");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/check")
    public ResponseEntity<?> check(String email) {
        boolean flag = userService.isDuplicateEmail(email);
        log.debug("{} 중복?? - {}",email,flag);
        return ResponseEntity.ok(flag);
    }

}
