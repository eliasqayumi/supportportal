package com.supportportal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Asia/Istanbul")
    private int httpStatusCode; // 200, 201, 400, 500
    private Date timeStamp;
    private HttpStatus httpStatus;
    private String reason;

    private String message;

    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message) {
        this.httpStatusCode = httpStatusCode;
        this.timeStamp = new Date();
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.message = message;
    }
}
