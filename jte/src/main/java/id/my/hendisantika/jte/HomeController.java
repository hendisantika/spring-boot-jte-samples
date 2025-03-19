package id.my.hendisantika.jte;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jte-samples
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 19/03/25
 * Time: 07.16
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {

    @GetMapping("")
    public String home(Model model) {
        Page page = new Page("Hello, Java Template Engine", "This is my home page");
        List<String> items = List.of("item1", "item2", "item3");
        model.addAttribute("name", "Itadori Yuji");
        model.addAttribute("page", page);
        model.addAttribute("items", items);
        return "index";
    }
}
