package com.jaygame.manager.filter;

import JwtUtil.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public String filterType() {
        return "pre";    // filter before
    }

    @Override
    public int filterOrder() {
        return 0;     // the Smaller number, the higher priority
    }

    /**
     * false menas not use
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;    //if current filter work
    }

    /**
     * @return: any return object means continuing operator
     * setSendZuulResponse(false) means interrupt operator
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println(" pass manager Filter");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        //login, go next step
        if(request.getRequestURI().indexOf("/user/login") > 0){
            return null;
        }

        String header = request.getHeader("Authorization");
        if(header != null){
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if("admin".equals(roles)){
                        //transfer header and go next step.
                        currentContext.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                }catch (Exception e){
                    currentContext.setSendZuulResponse(false);   //interrupt operation
                }
            }
        }
        currentContext.setSendZuulResponse(false); //interrupt operation
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("not enough right!!!");
        return null;    //operator in filter.
    }
}
