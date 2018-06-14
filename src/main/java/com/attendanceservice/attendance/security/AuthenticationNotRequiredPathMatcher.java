package com.attendanceservice.attendance.security;

public interface AuthenticationNotRequiredPathMatcher {

    boolean isRequestURIUnAuthorized(String requestURI);
    AuthenticationNotRequiredPathMatcher addEndPoint(String unAuthorizedURI);

}
