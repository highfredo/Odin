package es.us.isa.odin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.us.isa.odin.domain.Document;
import es.us.isa.odin.rest.CredentialsRest;
import es.us.isa.odin.rest.base.JsonModelAndView;


@Controller
@RequestMapping("/security")
public class UserAccountController {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(defaultValue = "false") boolean error) {
		
		ModelAndView mv = new JsonModelAndView();
		mv.addObject("msg", "logueate");
		
		if(error) mv.addObject("login_error", error);
		
		return mv;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@RequestBody CredentialsRest credentials) {
		
		ModelAndView mv = new JsonModelAndView();
		Document<UserAccount> user = userAccountService.signUp(credentials);
		mv.addObject(user);
		
		return mv;
	}
	
}
