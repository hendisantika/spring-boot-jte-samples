package id.my.hendisantika.jteforms.exceptions;

import id.my.hendisantika.jteforms.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jte-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 18/03/25
 * Time: 07.34
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model){
        User user = (User) ex.getBindingResult().getTarget();
        model.addAttribute("user", user);
        model.addAttribute("error","Please fill out all of the required form fields.");
        log.info("User validation failed for {}", user);
        return "index";
    }
}
