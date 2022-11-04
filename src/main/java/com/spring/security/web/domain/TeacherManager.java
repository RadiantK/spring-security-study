package com.spring.security.web.domain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TeacherManager implements AuthenticationProvider, InitializingBean {

    private Map<String, Teacher> teacherDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        TeacherAuthenticationToken token = (TeacherAuthenticationToken) authentication;
        if (teacherDB.containsKey(token.getCredentials())) {
            Teacher teacher = teacherDB.get(token.getCredentials());
            return TeacherAuthenticationToken.builder()
                    .principal(teacher)
                    .details(teacher.getUsername())
                    .authenticated(true)
                    .build();
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
//        return authentication == UsernamePasswordAuthenticationToken.class;
        return authentication == TeacherAuthenticationToken.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Teacher("teacher", "강선생", Set.of(new SimpleGrantedAuthority("ROLE_TEACHER")))
        ).forEach(t -> teacherDB.put(t.getId(), t));

    }
}
