package ru.practicum.shareit.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    String error;

    public ErrorResponse(String message) {
        this.error = message;
    }
}
