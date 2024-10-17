package com.hunter.user.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid",strategy = "uuid")
    private String id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)

    private String password;

    @Column(nullable = false)

    private String email;

    @CreationTimestamp
    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'COMMON'")
    private Role role;
}
