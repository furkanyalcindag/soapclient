package com.turkai.consume.jwt;


import org.apache.catalina.connector.RequestFacade;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
@ConditionalOnProperty(value = "security.enable", havingValue = "true")
public class JWTFilter implements Filter {
    protected JwtHelper jwt;

    /**
     * Yapıcı.
     *
     * @param jwtHelper
     */
    public JWTFilter(JwtHelper jwtHelper) {
        this.jwt = jwtHelper;
    }

    /**
     * JWT Filteresi gelen HTTP isteğinde Authorization başlığı olup olmadığını,
     * başlık varsa da taşıdığı token'ın geçerli bir JWT token olup olmadığını kontrol eder.
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String header = httpRequest.getHeader("Authorization");
        String a = ((RequestFacade) request).getRequestURI().toString();
        if (!a.contains("/actuator")) {
            if (header == null || header.equals("")) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "A valid authorization token must be provided.");
                return;
            }

            if (jwt.validateTokenFromHeader(header)) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The authorization token is not valid or expired.");
                return;
            }
        } else {
            chain.doFilter(request, response);
        }


    }
}
