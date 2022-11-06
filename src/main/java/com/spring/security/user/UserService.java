package com.spring.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 유저 등록
     *
     * @param email username
     * @param password password
     * @return 유저 권한을 가지고 있는 유저
     */
    public User signUp(String email, String password, GenderType gender, Integer age) {
        if (userRepository.findByEmail(email).orElse(null) != null) {
            throw new AlreadyRegisteredUserException();
        }
//        userRepository.findByEmail(email).orElseThrow(() -> new AlreadyRegisteredUserException());
        return userRepository.save(new User(
                email, passwordEncoder.encode(password), gender, age, "ROLE_USER"));
    }

    /**
     * 관리자 등록
     *
     * @param email username
     * @param password password
     * @return 관리자 권한을 가지고 있는 유저
     */
    public User singUpAdmin(String email, String password, GenderType gender, Integer age) {
        if (userRepository.findByEmail(email).orElse(null) != null) {
            throw new AlreadyRegisteredUserException();
        }

        return userRepository.save(new User(
                email, passwordEncoder.encode(password), gender, age, "ROLE_ADMIN"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
