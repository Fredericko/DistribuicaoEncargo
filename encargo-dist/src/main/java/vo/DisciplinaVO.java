package vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "disciplina")
public class DisciplinaVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String codigo;
	@Column(name = "nome_disciplina")
	private String nomeDisciplina;
	private String turno;
	@Column(name = "ch_total")
	private int chTotal;
	@Column(name = "ch_semanal")
	private int chSemanal;
	private String ementa;
	@OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(clause = "ordem")
	private Set<DocenteDisciplinaInteresseVO> docenteDisciplinaInteresse = new HashSet<DocenteDisciplinaInteresseVO>();
	@OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(clause = "ordem")
	private Set<DocenteDisciplinaMinistradaVO> docenteDisciplinaMinistrada = new HashSet<DocenteDisciplinaMinistradaVO>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getChTotal() {
		return chTotal;
	}

	public void setChTotal(int chTotal) {
		this.chTotal = chTotal;
	}

	public int getChSemanal() {
		return chSemanal;
	}

	public void setChSemanal(int chSemanal) {
		this.chSemanal = chSemanal;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	@Override
	public boolean equals(Object obj) {
		DisciplinaVO other = (DisciplinaVO) obj;
		if (this.id == other.id)
			return true;
		else
			return false;
	}

}
