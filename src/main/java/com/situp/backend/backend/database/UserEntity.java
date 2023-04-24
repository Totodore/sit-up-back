package com.situp.backend.backend.database;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Entity
public class UserEntity {
	@Getter
	private String id;

	@Getter
	private String name;

	@Getter
	private String email;
}
