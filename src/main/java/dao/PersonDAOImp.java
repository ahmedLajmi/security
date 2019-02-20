package dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import model.Person;


@Repository
public class PersonDAOImp implements PersonDAO {
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public void save(Person person) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(person);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> list() {
		Session session = this.sessionFactory.openSession();
		List<Person> personList = session.createQuery("from Person").list();
		session.close();
		return personList;
	}

	@Override
	public Person findById(int id) {
		Session session = this.sessionFactory.openSession();
		Person person = (Person) session.get(Person.class, id);
		session.close();
		return person;
	}

	@Override
	public void update(Person p) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(p);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(findById(id));
		tx.commit();
		session.close();
		
	}

	@Override
	public List<Person> search(Map<String, String> param) {
		String firstName = param.get("firstName");
		String lastName = param.get("lastName");
		String gender = param.get("gender");
		String hql = "FROM Person P ";
		if( !firstName.equals("") || !lastName.equals("") || !gender.equals("") ) {
			hql += "where " ;
		}
		//StringUtils.isEmpty(firstName)
		if(!firstName.equals("")) {
			hql += "P.firstName = '"+firstName+"'" ;
		}
		if(!firstName.equals("") && !lastName.equals("")) {
			hql += "and P.lastName = '"+lastName+"'" ;
		}
		if(firstName.equals("") && !lastName.equals("")) {
			hql += "P.lastName = '"+lastName+"'" ;
		}
		
		if((!firstName.equals("") || !lastName.equals("")) && !gender.equals("")) {
			hql += "and P.gender = '"+gender+"'" ;
		}
		if(firstName.equals("") && lastName.equals("") && !gender.equals("")) {
			hql += "P.gender = '"+gender+"'" ;
		}
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery(hql);
		List<Person> results = query.list();
		return results;
	}

}
