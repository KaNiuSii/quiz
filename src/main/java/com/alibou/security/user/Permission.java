package com.alibou.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_CREATE("admin:create"),

    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_DELETE("management:delete"),
    MANAGER_CREATE("management:create"),

    TEACHER_READ("teacher:read"),
    TEACHER_UPDATE("teacher:update"),
    TEACHER_DELETE("teacher:delete"),
    TEACHER_CREATE("teacher:create"),

    STUDENT_READ("student:read"),
    STUDENT_UPDATE("student:update");

    @Getter
    private final String permission;
}

