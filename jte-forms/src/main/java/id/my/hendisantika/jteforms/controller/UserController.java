package id.my.hendisantika.jteforms.controller;

import id.my.hendisantika.jteforms.entity.User;
import id.my.hendisantika.jteforms.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jte-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 18/03/25
 * Time: 07.33
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/save")
    public String saveUser(@Valid User user, Model model){
        userRepository.save(user);
        model.addAttribute("message","User information was saved successfully");
        return "index";
    }
}
