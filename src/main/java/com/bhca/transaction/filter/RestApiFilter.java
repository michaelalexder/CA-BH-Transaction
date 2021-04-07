package com.bhca.transaction.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Rest API filter. Adds request tracing data
 */
@Component
public class RestApiFilter extends OncePerRequestFilter {

    public static final String REQUEST_ID = "Request-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
      try {
          if (httpServletRequest.getHeader(REQUEST_ID) == null) {
              MDC.put(REQUEST_ID, UUID.randomUUID().toString());
          } else {
              MDC.put(REQUEST_ID, httpServletRequest.getHeader(REQUEST_ID));
          }
          filterChain.doFilter(httpServletRequest, httpServletResponse);
      } finally {
          MDC.remove(REQUEST_ID);
      }
    }
}
