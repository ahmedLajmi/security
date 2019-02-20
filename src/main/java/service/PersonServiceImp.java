package service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PersonDAO;
import model.Person;


@Service
public class PersonServiceImp implements PersonService {

	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	
	@Override
	public void addPerson(Person person) {
		this.personDAO.save(person);
		
	}

	@Override
	public List<Person> listPersons() {
		return this.personDAO.list();
	}


	@Override
	public Person findById(int id) {
		return this.personDAO.findById(id);		
	}


	@Override
	public void updatePerson(Person person) {
		this.personDAO.update(person);		
	}


	@Override
	public void deletePerson(int id) {
		this.personDAO.delete(id);
		
	}


	@Override
	public List<Person> searchPersons(Map<String, String> param) {
		return this.personDAO.search(param);
	}

}
