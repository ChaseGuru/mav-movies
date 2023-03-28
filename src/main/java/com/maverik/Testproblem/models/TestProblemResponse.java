package com.maverik.Testproblem.models;

public record TestProblemResponse<T>(boolean success, String error, ErrorCode errorCode, T data) {

}
