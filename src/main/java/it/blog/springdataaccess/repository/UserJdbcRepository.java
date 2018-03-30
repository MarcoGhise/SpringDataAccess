package it.blog.springdataaccess.repository;

import it.blog.springdataaccess.bean.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
@Profile("jdbc")
public class UserJdbcRepository implements UserRepository {

	private final String INSERT_SQL = "INSERT INTO USER(name, surname, age)  values(:name, :surname, :age)";

	private final String DELETE_SQL = "DELETE FROM USER WHERE (id=:id)";

	private final String FIND_ALL_BY_AGE = "SELECT id, name, surname, age FROM user WHERE age=:age";

	private final String FIND_ALL_BY_AGE_SP = "getUserByAge";

	private final String FIND_ALL_BY_SURNAME = "SELECT id, name, surname, age FROM user WHERE surname=:surname";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private DataSource dataSource;

	@Override
	public void save(User user) {

		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("name", user.getName())
				.addValue("surname", user.getSurname())
				.addValue("age", user.getAge());

		namedParameterJdbcTemplate.update(INSERT_SQL, parameters);
	}

	@Override
	public void delete(User user) {

		SqlParameterSource parameters = new MapSqlParameterSource().addValue(
				"id", user.getId());

		namedParameterJdbcTemplate.update(DELETE_SQL, parameters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllByAge(int age) {

		/*
		 * Randomly chose between Stored Procedure or Sql Query 
		 */
		Random r = new Random();
		
		if (r.nextInt(2) == 0) {

			/*
			 * Stored procedure
			 */
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(this.dataSource)
					.withProcedureName(FIND_ALL_BY_AGE_SP).returningResultSet(
							"users",
							BeanPropertyRowMapper.newInstance(User.class));

			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("i_age", age);

			SqlParameterSource in = new MapSqlParameterSource(inParamMap);

			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall
					.execute(in);
			return (List<User>) simpleJdbcCallResult.get("users");
			
		} else {
			/*
			 * Sql Query
			 */
			Map<String, Integer> parameters = new HashMap<String, Integer>();
			parameters.put("age", age);

			return (List<User>) namedParameterJdbcTemplate.query(
					FIND_ALL_BY_AGE, parameters, new UserMapper());
		}
	}

	@Override
	public List<User> findAllBySurname(String surname) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("surname", surname);

		return (List<User>) namedParameterJdbcTemplate.query(
				FIND_ALL_BY_SURNAME, parameters, new UserMapper());
	}

}
