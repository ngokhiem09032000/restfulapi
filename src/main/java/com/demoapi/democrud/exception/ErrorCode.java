package com.demoapi.democrud.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001,"Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002,"User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1003,"User not found", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1004,"Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1005,"Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1006,"User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1007,"Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008,"You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1009,"Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1010,"Token invalid", HttpStatus.UNAUTHORIZED),
    ROLE_NOT_FOUND(1011,"Role not found", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_FOUND(1012,"Permission not found", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
