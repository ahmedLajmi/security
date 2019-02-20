package dao;

import java.util.List;
import java.util.Map;

import model.Person;

public interface PersonDAO {

	public void save(Person p);
	public void update(Person p);
	public void delete(int id);
	public List<Person> search(Map<String, String> param);
	public Person findById(int id);
	public List<Person> list();

}
