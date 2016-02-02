package vo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "curso")
public class CursoVO implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome;
	private String duracao;

	@ManyToMany
	@JoinColumn(name="disciplina_id")
	private List<DisciplinaVO> disciplinas;
	
	public CursoVO(){
		nome="";
		duracao="";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public List<DisciplinaVO> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaVO> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
