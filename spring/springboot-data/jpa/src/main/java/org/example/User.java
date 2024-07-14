package org.example;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
@NamedNativeQueries(value = {
	@NamedNativeQuery(name = "User.nameNativeQueryById", query = "SELECT * FROM t_user WHERE id = ?1", resultClass = User.class),
})
public class User {
	@Id
//	@GeneratedValue
	private Long id;
	private String name;
	private String email;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User( String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	public User(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
}
