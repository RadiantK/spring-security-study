package com.spring.security.config;

import com.spring.security.note.NoteService;
import com.spring.security.notice.NoticeService;
import com.spring.security.user.GenderType;
import com.spring.security.user.User;
import com.spring.security.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
@Profile(value = "!test") // 테스트에서는 제외
public class InitializeDefaultConfig {

    private final UserService userService;
    private final NoteService noteService;
    private final NoticeService noticeService;

    /**
     * 유저등록, note 4개 등록
     */
    @Bean
    public void initializeDefaultUser() {
        User user = userService.signUp("user", "1234", GenderType.MALE, 22);
        noteService.saveNote(user, "테스트", "테스트문서입니다.");
        noteService.saveNote(user, "테스트2", "테스트문서입니다2.");
        noteService.saveNote(user, "테스트3", "테스트문서입니다3.");
        noteService.saveNote(user, "코딩 연습하기", "코딩 연습문서 입니다..");
    }

    /**
     * 어드민등록, 공지사항 2개 등록
     */
    @Bean
    public void initializeDefaultAdmin() {
        userService.singUpAdmin("admin", "1234", GenderType.MALE, 22);
        noticeService.saveNotice("환영합니다.", "환영합니다 여러분");
        noticeService.saveNotice("노트 작성 방법 공지", "1. 회원가입\n2. 로그인\n3. 노트 작성\n4. 저장\n* 본인 외에는 게시글을 볼 수 없습니다.");
    }
}
