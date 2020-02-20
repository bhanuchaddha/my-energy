package com.bhanuchaddha.myenergy.error.exception;

import com.bhanuchaddha.myenergy.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public class NotFoundException extends  RuntimeException {
    private final ErrorCode errorCode;
}