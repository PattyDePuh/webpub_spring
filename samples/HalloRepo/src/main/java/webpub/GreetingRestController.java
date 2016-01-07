package webpub;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class GreetingRestController {

    private static final String template = "Hallo, %s!";

    @Autowired
    GreetingsRepository repo;

    //Variante 2 (benötigt @RestController)
    //JSON output
    @RequestMapping("/hallo2")
    public Greeting greeting2(@RequestParam(value="name", defaultValue="World") String name) {
        Greeting greet = new Greeting( String.format(template, name));
        //Speichern
        repo.save(greet);
        
        return greet;
    }

}