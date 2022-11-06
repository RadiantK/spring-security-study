package com.spring.security.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        //given
        userRepository.save(new User("user123", "password", GenderType.MALE, 22, "ROLE_USER"));
        //when
        User user = userService.findByEmail("user123");
        //then
        assertThat(user.getId()).isNotNull();
    }
}