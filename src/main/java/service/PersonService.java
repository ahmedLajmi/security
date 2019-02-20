package service;

import java.util.List;
import java.util.Map;

import model.Person;


public interface PersonService {
	public void addPerson(Person person);
	public void updatePerson(Person person);
	public void deletePerson(int id);
	public List<Person> searchPersons(Map<String, String> param);
	public Person findById(int id);
	public List<Person> listPersons() ;
}
