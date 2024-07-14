package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "user")
public class User {
	@Id
	private Long id;
	private String name;
	private String password;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date created_time;

	public User(Long id, String name, String password, Date created_time) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.created_time = created_time;
	}
}
