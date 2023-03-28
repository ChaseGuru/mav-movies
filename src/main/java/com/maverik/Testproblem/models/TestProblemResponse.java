package com.maverik.Testproblem.models;

public record TestProblemResponse(boolean success, String error, ErrorCode errorCode, Object data) {

}
