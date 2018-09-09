//$Id:
package com.ti.techmania.glgservice.web;

import com.ti.util.email.Mailer;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * This class handles all exceptions thrown by the system. The default error
 * handling shows a generic error page and sends an email to the support team
 * with details of the error. You can add your own error handlers for specific
 * errors here.
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    //---- Members
    @Autowired
    private Mailer mailer;
    @Value("#{appProperties['emailSupport']}")
    private String supportEmail;
    public static final String ACCESS_DENIED_VIEW = "errors/accessDenied";
    public static final String BAD_REQUEST_VIEW = "errors/badRequest";
    public static final String DEFAULT_ERROR_VIEW = "errors/error";
    public static final String NOT_FOUND_VIEW = "errors/notFound";

    //---- Methods
    /**
     * Exception handler for AccessDeniedException.
     * @param ex The exception
     * @param request The HTTP request
     * @return redirection page
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException(final Throwable ex,
            final HttpServletRequest request) {
        return ACCESS_DENIED_VIEW;
    }

    /**
     * Exception handler for MissingServletRequestParameterException.
     * @param ex The exception
     * @param request The HTTP request
     * @return redirection page
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingServletRequestParameterException(
            final Exception ex, final HttpServletRequest request) {
        return BAD_REQUEST_VIEW;
    }

    /**
     * Exception handler for MissingServletRequestParameterException.
     * @param ex The exception
     * @param request The HTTP request
     * @return redirection page
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoHandlerFoundException(
            final Exception ex, final HttpServletRequest request) {
        return NOT_FOUND_VIEW;
    }

    /**
     * General exception handler for any Exception.
     * @param ex The exception
     * @param request The HTTP request
     * @return redirection page
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(final Throwable ex,
            final HttpServletRequest request) {

//        log.error("General exception handler invoked:", ex);
        // If support email is defined, send out an email
        // to notify of the error
        if (StringUtils.isNotBlank(supportEmail)) {
            try {
                // Retrieve the error stack trace
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                pw.close();

                // Send an email to the support team
                final String EMAIL_SUBJECT =
                        "[glgService] Unexpected Error Report - "
                        + ex.getMessage();
                mailer.sendEmail(supportEmail, EMAIL_SUBJECT, sw.toString());
            } catch (Exception e) {
//                log.debug("Failed to email {}:", supportEmail, e);
            }
        }

        return DEFAULT_ERROR_VIEW;
    }
}
