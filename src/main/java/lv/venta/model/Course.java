package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.DialectOverride.GeneratedColumns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "CourseTable")
@Entity
public class Course {
	
	@Column(name = "Idc")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idc;
	
	@Column(name = "Title", unique = true)
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[A-Ža-ž0-9 ]{3,40}")
	private String title;
	
	@Column(name = "Creditpoints")
	@Min(1)
	@Max(40)
	private int creditpoints;
	
	@ManyToMany //saite viens pret viens - katrā puse ir tikai viens otras klases objekts
	//liekam tajā klase, kuras tabulā gribēsim otras tabulas ārējo atslēgu
	//liekam otras klases pirmās atslēgas kolonas nosaukumu
	@JoinTable(name = "CourseProfTable",
	joinColumns = @JoinColumn(name="Idc"),
	inverseJoinColumns= @JoinColumn(name= "Idp"))
	private Collection<Professor> professor= new ArrayList<>();
	
	@OneToMany(mappedBy = "course")
	@ToString.Exclude
	private Collection<Grade> grades = new ArrayList<Grade>();
	
	
	public Course(String title, int creditpoints, Professor professor) {
		setTitle(title);
		setCreditpoints(creditpoints);
		addProfessor(professor);
		}
	public void addProfessor(Professor prof) {
		if(!professor.contains(prof)) {
			professor.add(prof);
		}
	}

}
