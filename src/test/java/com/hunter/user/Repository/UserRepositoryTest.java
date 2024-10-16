package com.hunter.user.Repository;

import com.hunter.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(false)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원가입 테스트")
    void saveTest(){
        User user = User.builder()
                .email("dlgy9714@gmail.com")
                .password("asdf1234")
                .username("이름")
                .build();

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
    }
    @Test
    @DisplayName("이메일로 찾기")
    void findByEmailTest(){
        //given
        String email = "test@test.com";
        //when
        Optional<User> userOptional = userRepository.findByEmail(email);

        //then
        assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        assertEquals("test", user.getUsername());

        System.out.println("\n\n\n");
        System.out.println("user = "+user);
        System.out.println("\n\n\n");
    }

    @Test
    @DisplayName("이메일 중복 체크")
    void EmailDuplicateTest(){
        //given
        String email = "test@test.com";
        //when
        boolean flag = userRepository.existsByEmail(email);
        //then
        assertTrue(flag);
    }
}