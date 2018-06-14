package com.attendanceservice.attendance.security;

import java.util.ArrayList;
import java.util.List;


public class AuthenticationNotRequiredPathMatcherImplementation implements AuthenticationNotRequiredPathMatcher {

    private List<String> unauthorizedURIs = new ArrayList<>();

    @Override
    public boolean isRequestURIUnAuthorized(String requestURI) {
        return unauthorizedURIs.stream().anyMatch(uri -> uri.equals(requestURI));
    }

    @Override
    public AuthenticationNotRequiredPathMatcher addEndPoint(String unAuthorizedURI) {
        unauthorizedURIs.add(unAuthorizedURI);
        return this;
    }


}
