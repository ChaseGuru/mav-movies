package com.maverik.testproblem.Testproblem.models;

public class TestProblemResponse {
    public enum ErrorCode {
        MAVERIK_REQUEST_FAILED,
    }

    private boolean success;
    private String error;
    private ErrorCode errorCode;
    private Object data;

    public TestProblemResponse(boolean success, String error, ErrorCode errorCode, Object data) {
        this.success = success;
        this.error = error;
        this.errorCode = errorCode;
        this.data = data;
    }
}
}
