package com.attendanceservice.attendance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationNotRequiredPathMatcher authenticationNotRequiredPathMatcherImplementation;
    private final JwtToken jwtToken;


    @Autowired
    public AuthenticationFilter(AuthenticationNotRequiredPathMatcher authenticationNotRequiredPathMatcherImplementation, JwtToken jwtToken) {
        this.authenticationNotRequiredPathMatcherImplementation = authenticationNotRequiredPathMatcherImplementation;
        this.jwtToken = jwtToken;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (authenticationNotRequiredPathMatcherImplementation.isRequestURIUnAuthorized(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);

        }else{
            Optional<String> token = Optional.empty();
            Optional<String> authorizationHeader = Optional.ofNullable(httpServletRequest.getHeader("Authorization"));
            Cookie cookie = WebUtils.getCookie(httpServletRequest, "attendance-please");

            Optional<String> cookieValue = Optional.ofNullable(cookie).map(Cookie::getValue);

            if(authorizationHeader.isPresent()){
                token = authorizationHeader;
            }
            if(cookieValue.isPresent()){
                token = cookieValue;
            }

            System.out.println("Token" + Optional.ofNullable(httpServletRequest.getHeader("Authorization")));
            if(!authorizationHeader.isPresent()&&!cookieValue.isPresent()) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Autherization token is not provided");

            }else{
                try {
                    if(jwtToken.verifyToken(token.get())){
                        if(jwtToken.isValid()){
                            filterChain.doFilter(httpServletRequest, httpServletResponse);
                            }else{
                            filterChain.doFilter(httpServletRequest, httpServletResponse);
                            //httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
                            }

                    }else{
                        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
