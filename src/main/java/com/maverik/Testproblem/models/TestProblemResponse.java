package com.maverik.Testproblem.models;

public record TestProblemResponse<T>(String error, ErrorCode errorCode, T data) {

}
