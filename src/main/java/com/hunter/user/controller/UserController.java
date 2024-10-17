package com.hunter.user.controller;

import com.hunter.exception.DuplicatedEmailException;
import com.hunter.exception.NoRegisteredArgumentsException;
import com.hunter.user.dto.request.LoginRequestDTO;
import com.hunter.user.dto.request.UserSignUpRequestDTO;
import com.hunter.user.dto.response.LoginResponseDTO;
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
    @GetMapping("/emailCheck")
    public ResponseEntity<?> check(String email) {
        boolean flag = userService.isDuplicateEmail(email);
        log.debug("{} 이메일중복?? - {}",email,flag);
        return ResponseEntity.ok(flag);
    }
    @GetMapping("/userNameCheck")
    public ResponseEntity<?> checkUserName(String userName) {
        boolean flag = userService.isDuplicateUserName(userName);
        log.debug("{} 이름중복?? - {}",userName,flag);
        return ResponseEntity.ok(flag);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> sighin(
            @Validated @RequestBody LoginRequestDTO dto
    ){
        try {
            LoginResponseDTO responseDTO=userService.authenticate(dto);
            log.info("로그인 성공 {}",responseDTO.getEmail());
            return ResponseEntity.ok(responseDTO);
        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
