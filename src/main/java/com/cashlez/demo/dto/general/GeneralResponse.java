package com.cashlez.demo.dto.general;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GeneralResponse {

    private String status;
    private String message;
    @JsonProperty("data")
    private Map<String, Object> data = new HashMap<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = " dd/MM/yyyy HH:mm:ss", timezone = "GMT+07:00")
    private Date timestamp = new Date();
    @JsonIgnore
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public GeneralResponse success() {
        this.status = "00";
        this.message = "Success";
        return this;
    }

    public GeneralResponse success(Object object) {
        this.status = "01";
        this.message = "Success";
        this.data.put("response", object);
        return this;
    }

    public GeneralResponse success(String status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }

    public GeneralResponse success(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.data.put("response", object);
        return this;
    }

    public GeneralResponse fail() {
        this.status = "02";
        this.message = "Fail";
        return this;
    }

    public GeneralResponse fail(Exception e) {
        this.status = "02";
        this.message = "Exception: " + e.getMessage();
        log.error("ERROR: " + ExceptionUtils.getStackTrace(e));
        return this;
    }

    public GeneralResponse fail(String str) {
        this.status = "02";
        this.message = "Exception: " + str;
        return this;
    }

    public GeneralResponse fail(String status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDataAsObject(Class objectClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(this.data.get("response"), objectClass);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "GeneralResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
