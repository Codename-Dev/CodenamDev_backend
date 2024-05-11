package codeNameDev.codeNameDev.member.domain;

import lombok.Getter;

public enum Role {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");


    @Getter
    private String value;

    Role(String value) {
        this.value = value;
    }
}
