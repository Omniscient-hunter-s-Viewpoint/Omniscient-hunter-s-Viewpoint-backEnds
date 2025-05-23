package com.hunter.user.dto.request;

import com.hunter.user.entity.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Size(min = 2, max = 20)
    private String username;

    //엔터티로 변환해주는 메서드
    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .username(this.username)
                .build();
    }

}
