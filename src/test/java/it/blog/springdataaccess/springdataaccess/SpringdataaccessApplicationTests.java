package it.blog.springdataaccess.springdataaccess;


import static org.junit.Assert.assertEquals;

import java.util.List;

import it.blog.springdataaccess.bean.User;
import it.blog.springdataaccess.repository.UserSpringDataRepository;
import it.blog.springdataaccess.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdataaccessApplicationTests {

	@Autowired 
	UserRepository repository;
	
	@Test
	public void saveUserTest() {
		
		/*
		 * Looking for an already inserted element
		 */
		List<User> userStored = repository.findAllBySurname("Robbins");
		
		if (userStored.size()>0)
			repository.delete(userStored.get(0));

		/*
		 * Insert the element
		 */		
		User user = new User();
		user.setName("Nik");
		user.setSurname("Robbins");
		user.setAge(55);
		
		repository.save(user);

		/*
		 * Looking for an element
		 */
		userStored = repository.findAllByAge(55);
		
		assertEquals(user.getName(), userStored.get(0).getName());
		assertEquals(user.getSurname(), userStored.get(0).getSurname());
		assertEquals(user.getAge(), userStored.get(0).getAge());
	}

}
