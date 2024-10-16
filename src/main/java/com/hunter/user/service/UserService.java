package com.hunter.user.service;

import com.hunter.exception.DuplicatedEmailException;
import com.hunter.exception.NoRegisteredArgumentsException;
import com.hunter.user.Repository.UserRepository;
import com.hunter.user.dto.request.UserSignUpRequestDTO;
import com.hunter.user.dto.response.UserSignUpResponseDTO;
import com.hunter.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSignUpResponseDTO create(UserSignUpRequestDTO dto){

        if(dto==null){
            throw new NoRegisteredArgumentsException("회원가입 입력정보가 없습니다!");
        }
        String email=dto.getEmail();

        if(userRepository.existsByEmail(email)){
            log.warn("이메일이 중복되었습니다 -{},",email);
            throw new DuplicatedEmailException("중복된 이메일");
        }
        User saved = userRepository.save(dto.toEntity(passwordEncoder));

        log.info("회원가입 성공! saved user -{}",saved );
        return new UserSignUpResponseDTO(saved);
    }
    public boolean isDuplicateEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
