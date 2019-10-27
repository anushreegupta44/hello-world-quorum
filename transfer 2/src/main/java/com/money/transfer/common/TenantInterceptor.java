package com.money.transfer.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

    private static final String requestNode = "(?<=\\/from\\/)(.*?)(?=\\/)";

    private final TenantContext tenantContext;

    public TenantInterceptor(TenantContext tenantContext) {
        this.tenantContext = tenantContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("HElloooooo");
        Pattern pattern = Pattern.compile(requestNode);
        Matcher matcher = pattern.matcher(request.getRequestURL());
        String fromNode;
        if (matcher.find()) {
            fromNode = matcher.group();
            System.out.println("Setting current tenant to " + fromNode);
            tenantContext.setCurrentTenant(fromNode);
            return true;
        }
        System.out.println("Not able to set current tenant");
        return false;
    }
}
