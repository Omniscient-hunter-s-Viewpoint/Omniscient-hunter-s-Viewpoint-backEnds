package com.hunter.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hunter.user.entity.User;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private String email;
    private String username;

    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDate joinDate;

    private String token;

    public LoginResponseDTO(User user,String token) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.joinDate=LocalDate.from(user.getJoinDate());
        this.token=token;
    }
}
