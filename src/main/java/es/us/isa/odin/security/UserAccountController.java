package es.us.isa.odin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.us.isa.odin.domain.Document;
import es.us.isa.odin.rest.base.JsonModelAndView;


@Controller
@RequestMapping("/security")
public class UserAccountController {
	
	@Autowired
	private UserAccountService userAccountService;

	@RequestMapping("/signup")
	public ModelAndView signup(@ModelAttribute Credentials credentials) {
		
		ModelAndView mv = new JsonModelAndView();
		Document<UserAccount> user = userAccountService.signUp(credentials);
		mv.addObject(user);
		
		return mv;
	}
	
}
