package it.blog.springdataaccess.repository;

import java.util.List;

import it.blog.springdataaccess.bean.User;

public interface UserRepository {

	public void save(User user);
	
	public void delete(User user);
	
	public List<User> findAllByAge(int age);
	
	public List<User> findAllBySurname(String surname);
}
