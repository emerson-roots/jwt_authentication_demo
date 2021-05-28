package demo.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.domain.dto.CredenciaisDTO;

@Controller
@RequestMapping(value = "/")
public class LoginResource {
	
	@GetMapping("/")
	public String login(CredenciaisDTO credsDTO) {
		return "login";
	}

	@PostMapping("/login")
	public String logar(CredenciaisDTO credsDTO, HttpServletRequest req, HttpServletResponse res) {
		System.out.println(credsDTO.getEmail());
		return "/usuarios";
	}

}
