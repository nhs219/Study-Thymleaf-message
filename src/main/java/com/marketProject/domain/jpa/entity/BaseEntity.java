package com.marketProject.domain.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
// setter는 웬만하면 닫는게 좋다.
public abstract class BaseEntity {
    private LocalDateTime createDatetime;
    private LocalDateTime updateDatetime;
    private LocalDateTime deleteDatetime;
}
