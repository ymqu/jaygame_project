package com.jaygame.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * Transfer header to next step
     * Because Header will lost when http request pass the zuul, so that "pre"
     * pass the zuul, get header info, then add to Zuul request header.
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //get request context
        RequestContext currentContext = RequestContext.getCurrentContext();
        //get request
        HttpServletRequest request = currentContext.getRequest();
        //get header
        String header = request.getHeader("Authorization");
        if( header !=null){
            currentContext.addZuulRequestHeader("Authorization", header);
        }
        return true;
    }

    /**
     * request header transfer.
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
