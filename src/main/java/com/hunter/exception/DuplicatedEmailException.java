package com.hunter.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicatedEmailException extends RuntimeException {

    public DuplicatedEmailException(String message) {
        super(message);
    }
}
