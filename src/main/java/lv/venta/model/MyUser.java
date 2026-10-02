package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "MyUserTable")
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
	@ManyToMany(mappedBy = "users")
	private Collection<MyAuthority> authorities = new ArrayList<MyAuthority>();
	public void addUser (MyAuthority authority) {
		if(!authorities.contains(authority)) {
			authorities.add(authority);
		}
	}
	public void removeUser(MyAuthority authority) {
		if (authorities.contains(authority)) {
			authorities.remove(authority);
		}
	}
	}
