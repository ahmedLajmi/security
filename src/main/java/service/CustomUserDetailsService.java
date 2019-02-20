package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import model.User;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userService.findById(login);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("test");
		System.out.println(hashedPassword);
        System.out.println("User : "+user); 
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        String role = user.getRole();
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        GrantedAuthority authority;
        if(role.equals("ROLE_ADMIN")) {
            authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        }else {
        	authority = new SimpleGrantedAuthority("ROLE_USER");
        }
        grantList.add(authority);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), 
             true, true, true, true, grantList);
	}

}
