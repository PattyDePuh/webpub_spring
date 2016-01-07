package webpub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

@Controller
class ListController{

	@Autowired
    GreetingsRepository repo;
	
	//Liste aller Greetings (benötigt @Controller)
    @RequestMapping("/list")
    public String greetingList(Model model) {
    	model.addAttribute( "namelist", repo.findAll() );
        return "guestlist";
    }
}