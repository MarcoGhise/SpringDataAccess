package it.blog.springdataaccess.repository;

import java.util.List;

import it.blog.springdataaccess.bean.User;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
@Profile("springdata")
public interface UserSpringDataRepository extends UserRepository, Repository<User, Integer>{

    @Query("SELECT user FROM User user WHERE age=:age")
	public List<User> findAllByAge(@Param("age") int age);
    
    @Query("SELECT user FROM User user WHERE surname=:surname")
	public List<User> findAllBySurname(@Param("surname") String surname);
}
