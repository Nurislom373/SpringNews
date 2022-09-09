package org.khasanof.jpaspecification.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationError {
    private String code;
    private String path;
    private Integer status;
    private String message;
    private LocalDateTime time;
    private WebRequest request;

    public ApplicationError(String message, WebRequest webRequest, HttpStatus httpStatus) {
        this(message, ((ServletWebRequest) webRequest).getRequest().getRequestURI(), httpStatus);
    }

    public ApplicationError(String message, String path, HttpStatus httpStatus) {
        this.path = path;
        this.message = message;
        this.time = LocalDateTime.now();
        this.status = httpStatus.value();
        this.code = httpStatus.getReasonPhrase();
    }

    @Builder
    public ApplicationError(HttpStatus status, String message, String path, WebRequest request) {
        this.path = path;
        this.message = message;
        this.request = request;
        this.status = status.value();
        this.time = LocalDateTime.now();
        this.code = status.getReasonPhrase();
    }
}
