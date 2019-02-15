package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDAO;
import model.User;

@Service
public class UserServiceImp implements UserService {

	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
	@Override
	public void addUser(User user) {
		this.userDAO.save(user);
		
	}

	@Override
	public List<User> listUsers() {
		return this.userDAO.list();
	}


	@Override
	public User findById(int id) {
		return this.userDAO.findById(id);		
	}

}
