package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class UserDAOImp implements UserDAO {
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public void save(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		Session session = this.sessionFactory.openSession();
		List<User> userList = session.createQuery("from User").list();
		session.close();
		return userList;
	}

	@Override
	public User findById(int id) {
		Session session = this.sessionFactory.openSession();
		User user = (User) session.get(User.class, id);
		session.close();
		return user;
	}

}
