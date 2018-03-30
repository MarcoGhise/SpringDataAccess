package it.blog.springdataaccess.repository;

import java.util.List;

import it.blog.springdataaccess.bean.User;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
@Profile("jpanamedquery")
public interface UserJpaNamedQueryRepository extends UserRepository, Repository<User, Integer>{

	public List<User> findAllByAge(@Param("age") int age);

	public List<User> findAllBySurname(@Param("surname") String surname);
}
