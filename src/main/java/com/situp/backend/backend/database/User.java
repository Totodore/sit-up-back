package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String firstname;

	private String email;

	private String password;

	private Date birthdate;

	private boolean admin;

	//@OneToMany
	//private List<Preferences> prefs;

}
