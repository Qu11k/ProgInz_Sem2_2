package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Setter;

public class MyUser {
	@Column(name = "Idu")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)//priekš ids nebūs set funkcija
	private long ids;
	
	@Column(name = "Username",unique = true)
	@NotNull
	@NotEmpty
	private String username;
	@Column(name = "Password")
	@NotNull
	@NotEmpty
	private String password;

		
	}
