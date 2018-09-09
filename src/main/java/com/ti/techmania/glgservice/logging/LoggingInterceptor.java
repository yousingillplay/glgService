//$Id: LoggingInterceptor.java,v 1.1 2018/04/19 06:46:32 a0284538 Exp $
package com.ti.techmania.glgservice.logging;

import com.ti.spring.web.security.logging.TISecurityLogger;
import com.ti.ta.J2eeLogin;
import com.ti.util.cookie.CookieUtil;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor, Serializable {

    //---- Members
    private static final long serialVersionUID = 1L;

    private static final String REQUEST_KEY = "uid";
    private static final String PRINCIPAL_REQUEST_COOKIE = "TIPASSID";
    private String principalRequestCookie = "localhostTIPASSID";
    private String cookieKey = "uid";

    @Autowired
    private TISecurityLogger tiSecurityLogger;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler)
            throws Exception {
        try {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpSession session = servletRequest.getSession(false);

            //Check if we have a valid spring security context
            SecurityContext sc = null;
            if (session != null) {
                sc = (SecurityContext) session
                        .getAttribute("SPRING_SECURITY_CONTEXT");
            }

            String principal;
            //Check the request first. This is where it is put after initial login.
            //Due to J2eeLogin setting localhost in the reponse immediately before
            // this is called, we can't rely on cookie after the initial login.
            if ("localhost".equalsIgnoreCase(request.getServerName())
                    && request.getParameter(REQUEST_KEY) != null) {
                principal = request.getParameter(REQUEST_KEY);
            } else {
                String cookieValue;
                if ("localhost".equalsIgnoreCase(request.getServerName())) {
                    //Look for the localhost cookie set by J2eeLogin
                    //Localhosts are special because we cannot read the cookie from
                    //the .ti.com domain.
                    cookieValue = J2eeLogin.getCookieValue(servletRequest, principalRequestCookie);
                } else {
                    //Looking for the TIPASS cookie for normal servers
                    cookieValue = J2eeLogin.getCookieValue(servletRequest,
                            PRINCIPAL_REQUEST_COOKIE);
                }
                principal = CookieUtil.parseTipassCookie(cookieValue, cookieKey);
            }
//            log.trace("LoggingInterceptor: Principal --> {}", principal);

            if (sc == null && StringUtils.isNotBlank(principal)) {
                tiSecurityLogger.logUserFirstSeen(principal);
//                log.debug("LoggingInterceptor: User First Seen --> {}", principal);
            }
        } catch (Throwable t) {
//            log.error("Error in LoggingInterceptor.java", t);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
        // not handled
    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
        // not handled
    }

}
