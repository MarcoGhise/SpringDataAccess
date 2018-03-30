package it.blog.springdataaccess.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
		@NamedQuery(name = "User.findAllByAge", query = "SELECT user FROM User user WHERE age=:age"),
		@NamedQuery(name = "User.findAllBySurname", query = "SELECT user FROM User user WHERE surname=:surname") })

public class User {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String surname;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
