//$Id: TISecurityLoggingAspect.java,v 1.9 2018/04/06 08:56:26 a0284538 Exp $
package com.ti.techmania.glgservice.logging;

import com.ti.spring.web.security.logging.TISecurityLogger;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Aspect
@Component
@Slf4j
public class TISecurityLoggingAspect {

    @Autowired
    private TISecurityLogger tiSecurityLogger;

//    @AfterReturning("execution(* com.ti.spring.service.usersvc.module.service.UserService.addUser(..)) && args(userId)")
    public void logAddUser(JoinPoint joinPoint, String userId) {
        tiSecurityLogger.logAddUser(userId);
    }

//    @AfterReturning("execution(* com.ti.spring.service.usersvc.module.service.UserService.deleteUser(..)) && args(userId)")
    public void logDeleteUser(JoinPoint joinPoint, String userId) {
        tiSecurityLogger.logRemoveUser(userId);
    }

//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.UserService.addAuthority(..)) && args(userId, authority)")
    public void logAddAuthority(JoinPoint joinPoint, String userId, String authority) {
        tiSecurityLogger.logAddUserPrivilege(userId, authority);
    }

//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.UserService.addAuthorities(..)) && args(userIds, authorities)")
    public void logAddAuthorities(JoinPoint joinPoint, List<String> userIds, List<String> authorities) {
        for (String authority : authorities) {
            for (String userId : userIds) {
                tiSecurityLogger.logAddUserPrivilege(userId, authority);
            }
        }
    }

//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.UserService.deleteAuthority(..)) && args(userId, authority)")
    public void logDeleteAuthority(JoinPoint joinPoint, String userId, String authority) {
        tiSecurityLogger.logRemoveUserPrivilege(userId, authority);
    }

//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.UserService.deleteAuthorities(..)) && args(userIds, authorities)")
    public void logDeleteAuthorities(JoinPoint joinPoint, List<String> userIds, List<String> authorities) {
        for (String authority : authorities) {
            for (String userId : userIds) {
                tiSecurityLogger.logRemoveUserPrivilege(userId, authority);
            }
        }
    }
    
//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.UserService.delegateAuthority(String, String, String, java.util.Date, java.util.Date))"
//                    + " && args(delegatedFrom, delegatedTo, authority, startDttm, endDttm)")
    public void logDelegateAuthority(JoinPoint joinPoint, 
            String delegatedFrom, String delegatedTo, String authority, Date startDttm, Date endDttm) {
        tiSecurityLogger.logDelegateAuthority(delegatedFrom, delegatedTo, authority, startDttm, endDttm);
    }
    
//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.UserService.delegateAuthority(String, String, String, java.util.Date))"
//                    + " && args(delegatedFrom, delegatedTo, authority, endDttm)")
    public void logDelegateAuthority(JoinPoint joinPoint, 
            String delegatedFrom, String delegatedTo, String authority, Date endDttm) {
        tiSecurityLogger.logDelegateAuthority(delegatedFrom, delegatedTo, authority, null, endDttm);
    }

//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.UserService.addMembership(..)) && args(userId, groupPath)")
    public void logAddMembership(JoinPoint joinPoint, String userId, String groupPath) {
        tiSecurityLogger.logAddUserToGroup(userId, groupPath);
    }

//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.UserService.removeMembership(..)) && args(userId, groupPath)")
    public void logRemoveMembership(JoinPoint joinPoint, String userId, String groupPath) {
        tiSecurityLogger.logRemoveUserFromGroup(userId, groupPath);
    }

//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.GroupService.add*GroupValue(..)) && args(parentGroupPath, parentValuePath, groupName, groupValue)")
    public void logAddGroup(JoinPoint joinPoint, String parentGroupPath, String parentValuePath, String groupName, String groupValue) {
        tiSecurityLogger.logAddGroup(parentGroupPath + "|" + groupName);
    }

//    @AfterReturning(
//            value = "execution(* com.ti.spring.service.usersvc.module.service.GroupService.remove*GroupValue(..)) && args(groupPath, valuePath)")
    public void logRemoveGroup(JoinPoint joinPoint, String groupPath, String valuePath) {
        tiSecurityLogger.logRemoveGroup(groupPath);
    }

    /**
     * Populate TI security logging object. This will be used by the custom
     * Log4j appender (TISecurityLoggingAppender) and layout
     * (TISecurityLoggingLayout) if it is passed as a parameter into the Log4j
     * call. 
     * 
     * NOTE: SLF4J will not work here.
     *
     * @param joinPoint The JoinPoint object.
     * @param error The thrown error.
     */
    @AfterThrowing(
            value = "@annotation(requestMapping) && execution(* com.ti.techmania.glgservice.web.*.*(..))",
            throwing = "error")
    public void logAppError(JoinPoint joinPoint, RequestMapping requestMapping, Throwable error) {
        try {
            tiSecurityLogger.logAppError(
                    error.getMessage(), 
                    "Application error",
                    joinPoint.toString() + " triggered by " + requestMapping.value()
            );
//            log.error("General exception handler invoked:", error);
        } catch (Throwable e) {
//            log.warn("Failed to perform security logging", e);
        }
    }
}
