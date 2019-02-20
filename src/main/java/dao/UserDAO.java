package dao;

import java.util.List;

import model.User;

public interface UserDAO {

	public void save(User p);
	public User findById(String login);
	public List<User> list();

}
