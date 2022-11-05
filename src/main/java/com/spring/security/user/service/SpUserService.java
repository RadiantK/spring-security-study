package com.spring.security.user.service;

import com.spring.security.user.domain.SpAuthority;
import com.spring.security.user.domain.SpUser;
import com.spring.security.user.repository.SpUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class SpUserService implements UserDetailsService {

    private final SpUserRepository userRepository;

    public SpUserService(SpUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findSpUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Optional<SpUser> findUser(String email) {
        return userRepository.findSpUserByEmail(email);
    }

    public SpUser save(SpUser spUser) {
        return userRepository.save(spUser);
    }

    public void addAuthority(Long userId, String authority) {
        userRepository.findById(userId).ifPresent(user -> {
            SpAuthority newRole = new SpAuthority(user.getUserId(), authority);

            if (user.getAuthorities() == null) {
                Set<SpAuthority> authorities = new HashSet<>();
                authorities.add(newRole);
                user.setAuthorities(authorities);
                save(user);
            } else if (!user.getAuthorities().contains(newRole)) {
                Set<SpAuthority> authorities = new HashSet<>();
                authorities.addAll(user.getAuthorities());
                authorities.add(newRole);
                user.setAuthorities(authorities);
                save(user);
            }
        });
    }

    public void removeAuthority(Long userId, String authority) {
        userRepository.findById(userId).ifPresent(user -> {
            if (user.getAuthorities() == null) return;

            SpAuthority targetRole = new SpAuthority(user.getUserId(), authority);
            if (user.getAuthorities().contains(targetRole)) {
                user.setAuthorities(
                        user.getAuthorities().stream()
                                .filter(auth -> !auth.equals(targetRole))
                                .collect(Collectors.toSet())
                );
                save(user);
            }
        });
    }
}
