package com.bms.usersdemo.core.utilities;

public class ErrorResult extends Result {
    public ErrorResult() {
        super(false);
    }
    
    public ErrorResult(String message) {
        super(false, message);
    }
}
