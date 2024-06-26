package fish.payara.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpsRedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getScheme().equals("http")) {
            String redirectUrl = "https://" + httpRequest.getServerName()
                    + ":" + 8181 // Default Payara HTTPS port
                    + httpRequest.getRequestURI();
            if (httpRequest.getQueryString() != null) {
                redirectUrl += "?" + httpRequest.getQueryString();
            }
            httpResponse.sendRedirect(redirectUrl);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}

