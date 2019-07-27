package flipr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;

	
	@RequestMapping(value = "/{username}/{password}")
	public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
		return loginService.result(username,password);
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/add/{username}/{password}")
	public String addAccount(@PathVariable("username") String username, @PathVariable("password") String password)
	{
		Login login = new Login(username,password);
		if(loginService.add(login)==1)
		{
			return "Signup Successful!";			
		}
		else
		{
			return "Signup unsuccessful!";
		}
	}

	
	
	
	
	
}