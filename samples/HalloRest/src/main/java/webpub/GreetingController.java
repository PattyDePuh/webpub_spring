package webpub;

import org.springframework.stereotype.Controller;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;

@Controller
@RestController
public class GreetingController {

    private static final String template = "Hallo, %s!";
    private final AtomicLong counter = new AtomicLong();

    //Variante 1 (benötigt @RestController)
    //simpler String output
    @RequestMapping("/hallo1")
    public String greeting1(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

    //Variante 2 (benötigt @RestController)
    //JSON output
    @RequestMapping("/hallo2")
    public Greeting greeting2(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    //Variante 3 (benötigt @Controller)
    //dynamische HTML-Seite
    @RequestMapping("/hallo3")
    public String greeting3(@RequestParam(value="name", defaultValue="World") String name, Model model) {
    	model.addAttribute("name", name);
        return "hello_temp";
    }

}