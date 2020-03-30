package web.tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	
		@GetMapping("/customLogin")
		public String customLogin() {
			
			return "fancy-login";
			
		}

	@GetMapping("/exceptionPage")
	public String exceptionController() {
		
		return "exception-page";
		
	}
}

