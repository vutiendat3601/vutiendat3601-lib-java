package vn.io.vutiendat3601.lib.logging.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class CorrelationIdFilter implements Filter {
  private static final String X_CORRELATION_ID_HEADER = "X-Correlation-Id";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    final HttpServletRequest httpRequest = (HttpServletRequest) request;
    try {
      Optional.ofNullable(httpRequest.getHeader(X_CORRELATION_ID_HEADER))
          .ifPresentOrElse(
              correlationId -> {
                MDC.put(X_CORRELATION_ID_HEADER, correlationId);
              },
              () -> {
                log.warn("Correlation ID is missing in the request headers.");
              });

      chain.doFilter(request, response);
    } finally {
      MDC.remove(X_CORRELATION_ID_HEADER);
    }
  }
}
