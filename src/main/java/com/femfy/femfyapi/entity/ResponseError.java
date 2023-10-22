package com.femfy.femfyapi.entity;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ResponseError {
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;


    public ResponseError(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
    }


    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}