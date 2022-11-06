package com.spring.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {

    private final UserService userService;

    private GenderType[] getGenders() {
        return GenderType.values();
    }

    /**
     * @return 회원가입 페이지 리소스
     */
    @GetMapping
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("genderType", getGenders());
        return "signup";
    }

    @PostMapping
    public String signUp(@ModelAttribute UserRegisterDto userDto) {
        System.out.println("userDto = " + userDto);
        userService.signUp(userDto.getEmail(), userDto.getPassword(), userDto.getGender(), userDto.getAge());
        // 회원가입 후 로그인 페이지로 이동
        return "redirect:login";
    }
}
