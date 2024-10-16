package com.hunter.user.service;
import com.hunter.user.dto.request.UserSignUpRequestDTO;
import com.hunter.user.dto.response.UserSignUpResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(false)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("서비스 회원가입 테스트")
    void saveTest(){
        //given
        UserSignUpRequestDTO dto=UserSignUpRequestDTO.builder()
                .email("bbbbb@gmail.com")
                .password("bbbbbbbbbbb1")
                .username("하암")
                .build();
        //when

        UserSignUpResponseDTO responseDTO =userService.create(dto);
        //then
        assertEquals("하암",responseDTO.getUsername());

        System.out.println("\n\n\n");
        System.out.println("dto = "+responseDTO);
        System.out.println("\n\n\n");
    }

}