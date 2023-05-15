package com.philips.dmis.swt.ui.toolkit.dto;

public class Status {
    private StatusType type;
    private String message;

    //todo: maybe add a hint to which part of the request caused the problem

    public Status(StatusType type, String message) {
        this.type = type;
        this.message = message;
    }

    public StatusType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
