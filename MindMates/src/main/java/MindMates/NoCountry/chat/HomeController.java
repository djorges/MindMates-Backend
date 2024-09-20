package MindMates.NoCountry.chat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: Backend
 * Author: djorges
 * Date: 19/9/2024
 */

@RestController
@RequestMapping("api/home")
public class HomeController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hola, Mundo";
    }
}
