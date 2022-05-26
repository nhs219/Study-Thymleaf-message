package com.marketProject.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultList<T> {
    private int count;
    private T result;
}
