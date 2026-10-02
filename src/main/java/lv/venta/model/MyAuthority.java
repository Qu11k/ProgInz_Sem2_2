package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "MyAuthorityTable")
public class MyAuthority {
	@Column(name = "Ida")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)//priekš ids nebūs set funkcija
	private long ids;
	@Column(name = "Title", unique = true)
	@NotNull
	@NotEmpty
	@Pattern(regexp= "[A-Z_]{3,15}")
	private String title;
	@ManyToMany
	@JoinTable(name = "AuthUserTable",
	inverseJoinColumns = @JoinColumn (name = "MyUserTable"),
	joinColumns = @JoinColumn(name = "MyAuthorityTable"))
	@ToString.Exclude
	private Collection<MyUser> users = new ArrayList<MyUser>();
	public void addUser (MyUser user) {
		if(!users.contains(user)) {
			users.add(user);
		}
	}
	public void removeUser(MyUser user) {
		if (users.contains(user)) {
			users.remove(user);
		}
	}
}
