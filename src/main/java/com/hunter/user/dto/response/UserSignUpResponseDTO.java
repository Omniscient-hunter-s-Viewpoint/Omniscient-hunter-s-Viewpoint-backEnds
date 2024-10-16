package com.hunter.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hunter.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public  class UserSignUpResponseDTO {

    private String email;
    private String username;

    @JsonProperty("join-date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinDate;

    public UserSignUpResponseDTO(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.joinDate = user.getJoinDate();
    }
}
