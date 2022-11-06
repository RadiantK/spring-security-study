package com.spring.security.note;

import lombok.Getter;
import lombok.Setter;

/**
 * 노트 등록
 */
@Getter
@Setter
public class NoteRegisterDto {

    private String title;
    private String content;
}