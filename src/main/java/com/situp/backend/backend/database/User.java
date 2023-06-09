package com.situp.backend.backend.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

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

	@OneToOne
	private HouseLookupPreferences prefs;
}
