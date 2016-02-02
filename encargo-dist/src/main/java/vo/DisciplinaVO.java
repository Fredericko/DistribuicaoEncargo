package vo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "disciplina")
public class DisciplinaVO implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome_disciplina;
	private String turno;
	private int ch_total;
	private int ch_semanal;
	private String ementa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome_disciplina() {
		return nome_disciplina;
	}

	public void setNome_disciplina(String nome_disciplina) {
		this.nome_disciplina = nome_disciplina;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getCh_total() {
		return ch_total;
	}

	public void setCh_total(int ch_total) {
		this.ch_total = ch_total;
	}

	public int getCh_semanal() {
		return ch_semanal;
	}

	public void setCh_semanal(int ch_semanal) {
		this.ch_semanal = ch_semanal;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

}
