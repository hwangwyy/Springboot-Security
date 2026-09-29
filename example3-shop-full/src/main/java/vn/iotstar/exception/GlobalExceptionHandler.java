package vn.iotstar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Turns unexpected service errors into the friendly templates/error.html page. */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBusiness(RuntimeException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(MailException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleMail(MailException ex, Model model) {
        model.addAttribute("message", "Không gửi được email. Hãy kiểm tra cấu hình SMTP (MAIL_* trong .env).");
        return "error";
    }
}
