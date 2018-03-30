package it.blog.springdataaccess.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import it.blog.springdataaccess.bean.User;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setAge(rs.getInt("age"));
		return user;
	}

}
 