package it.blog.springdataaccess.repository;

import java.util.List;

import it.blog.springdataaccess.bean.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
public class UserJpaRepository implements UserRepository{

    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    @Override
    public void save(User user) {
    	if (user.getId() == 0) {
    		this.em.persist(user);     		
    	}
    	else {
    		this.em.merge(user);    
    	}
    }

    @Transactional
	@Override
	public void delete(User user) {
		this.em.remove(em.contains(user) ? user : em.merge(user));
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllByAge(int age) {
		Query query = this.em.createQuery("SELECT user FROM User user WHERE age=:age");
        query.setParameter("age", age);
        return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllBySurname(String surname) {
		Query query = this.em.createQuery("SELECT user FROM User user WHERE surname=:surname");
        query.setParameter("surname", surname);
        return query.getResultList();
	}
}
