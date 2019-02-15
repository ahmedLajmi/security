package dao;

import java.util.List;

import model.User;

public interface UserDAO {

	public void save(User p);
	public User findById(int id);
	public List<User> list();

}
