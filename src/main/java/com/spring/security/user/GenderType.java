package com.spring.security.user;

import lombok.Getter;

@Getter
public enum GenderType {
    MALE("male"), FEMALE("female");

    private String text;

    GenderType(String text) {
        this.text = text;
    }
}
