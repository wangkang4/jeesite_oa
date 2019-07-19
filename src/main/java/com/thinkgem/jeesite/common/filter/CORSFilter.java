package com.thinkgem.jeesite.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTML5应用跨域问题
 *
 * @author wchao
 * @ClassName COSFilter
 * @date 2017年3月28日 下午1:07:41
 */
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.addHeader ( "Access-Control-Allow-Origin", "*" );
        httpResponse.setHeader ( "Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, REQUEST_TOKEN" );
        chain.doFilter ( request, response );
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
