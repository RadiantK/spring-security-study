package com.spring.security.user;

import lombok.*;

/**
 * 유저 회원가입용 Dto
 */
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class
UserRegisterDto {

    private String email;
    private String password;
    private GenderType gender;
    private Integer age;
}
