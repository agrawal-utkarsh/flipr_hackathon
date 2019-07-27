package flipr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	public String result(String username, String password) {
		
		ArrayList<Login> list = new ArrayList<Login>();
		loginRepository.findAll().forEach(list::add);
		for(Login l : list)
		{
			if(l.getUsername().equals(username) && l.getPassword().equals(password))
			{
				Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
				String jws = Jwts.builder().setSubject(username).signWith(key).compact();

				return jws;
			}
			else
			{
				return "login fail";
			}
		}
		return "login fail";
	}

	public Integer add(Login l)
	{
		loginRepository.save(l);
		return 1;
	}
}

