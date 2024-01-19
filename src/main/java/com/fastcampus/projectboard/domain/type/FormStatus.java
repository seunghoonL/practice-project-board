package com.fastcampus.projectboard.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FormStatus {

    CREATE("저장", false),

    UPDATE("수정", true),
    ;

    private final String description;

    private final boolean update;


}
