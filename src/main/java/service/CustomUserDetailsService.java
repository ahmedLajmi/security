package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import model.User;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findById(Integer.parseInt(username));
        System.out.println("User : "+user);
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        grantList.add(authority);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
                 true, true, true, true, grantList);
	}

}
