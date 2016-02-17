package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "docente")
public class DocenteVO implements Serializable {

	@Id
	private String id;
	private String nome;
	private String sobrenome;
	private String senha;
	@Column(name = "regime_trabalho")
	private double regimeTrabalho;
	@Column(name = "contrato_natureza")
	private String contratoNatureza;
	@Column(name = "contrato_encerramento")
	private Date contratoEncerramento;
	@OneToMany(mappedBy = "primaryKey.docente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocenteDisciplinaInteresseVO> docente_disciplina_interesse = new ArrayList<DocenteDisciplinaInteresseVO>();
	@OneToMany(mappedBy = "primaryKey.docente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocenteDisciplinaMinistradaVO> docente_disciplina_ministrada = new ArrayList<DocenteDisciplinaMinistradaVO>();
	@Transient
	private int contemplacao;
	@Transient
	private double horasMinistradas;
	@Transient
	private List<DisciplinaVO> disciplinasInteresse = new ArrayList<DisciplinaVO>();
	@Transient
	private List<DisciplinaVO> disciplinasMinistradas = new ArrayList<DisciplinaVO>();

	public DocenteVO() {
		setId(UUID.randomUUID().toString());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getRegimeTrabalho() {
		return regimeTrabalho;
	}

	public void setRegimeTrabalho(double regimeTrabalho) {
		this.regimeTrabalho = regimeTrabalho;
	}

	public String getContratoNatureza() {
		return contratoNatureza;
	}

	public void setContratoNatureza(String contratoNatureza) {
		this.contratoNatureza = contratoNatureza;
	}

	public Date getContratoEncerramento() {
		return contratoEncerramento;
	}

	public void setContratoEncerramento(Date contratoEncerramento) {
		this.contratoEncerramento = contratoEncerramento;
	}

	public int getContemplacao() {
		return contemplacao;
	}

	public void setContemplacao(int contemplacao) {
		this.contemplacao = contemplacao;
	}

	public List<DocenteDisciplinaInteresseVO> getDocenteDisciplinaInteresse() {
		return docente_disciplina_interesse;
	}

	public void setDocenteDisciplinaInteresse(List<DocenteDisciplinaInteresseVO> docente_disciplina_interesse) {
		this.docente_disciplina_interesse = docente_disciplina_interesse;
	}

	public List<DocenteDisciplinaMinistradaVO> getDocenteDisciplinaMinistrada() {
		return docente_disciplina_ministrada;
	}

	public void setDocenteDisciplinaMinistrada(List<DocenteDisciplinaMinistradaVO> docente_disciplina_ministrada) {
		this.docente_disciplina_ministrada = docente_disciplina_ministrada;
	}

	public List<DocenteDisciplinaInteresseVO> getDocenteDisciplinasInteresse() {
		return docente_disciplina_interesse;
	}

	public List<DocenteDisciplinaMinistradaVO> getDocenteDisciplinasMinistradas() {
		return docente_disciplina_ministrada;
	}

	public List<DisciplinaVO> getDisciplinasInteresse() {
		disciplinasInteresse.clear();
		for (DocenteDisciplinaInteresseVO disc : docente_disciplina_interesse) {
			disciplinasInteresse.add(disc.getDisciplina());
		}
		return disciplinasInteresse;
	}

	public DisciplinaVO getDisciplinasInteresse(int i) {
		disciplinasInteresse.clear();
		for (DocenteDisciplinaInteresseVO disc : docente_disciplina_interesse) {
			disciplinasInteresse.add(disc.getDisciplina());
		}
		return disciplinasInteresse.get(i);
	}

	public void setDisciplinasInteresse(List<DisciplinaVO> disciplinas) {
		this.disciplinasInteresse = disciplinas;
		docente_disciplina_interesse.clear();
		int i = 0;
		for (DisciplinaVO disc : this.disciplinasInteresse) {
			docente_disciplina_interesse.add(new DocenteDisciplinaInteresseVO());
			docente_disciplina_interesse.get(i).setOrdem(i);
			docente_disciplina_interesse.get(i).setDocente(this);
			docente_disciplina_interesse.get(i).setDisciplina(disc);
			i++;
		}
	}

	public List<DisciplinaVO> getDisciplinasMinistradas() {
		disciplinasMinistradas.clear();
		for (DocenteDisciplinaMinistradaVO disc : docente_disciplina_ministrada) {
			disciplinasMinistradas.add(disc.getDisciplina());
		}
		return disciplinasMinistradas;
	}

	public void setDisciplinasMinistradas(List<DisciplinaVO> disciplinas) {
		this.disciplinasMinistradas = disciplinas;
		docente_disciplina_ministrada.clear();
		int i = 0;
		for (DisciplinaVO disc : this.disciplinasMinistradas) {
			docente_disciplina_ministrada.add(new DocenteDisciplinaMinistradaVO());
			docente_disciplina_ministrada.get(i).setOrdem(i);
			docente_disciplina_ministrada.get(i).setDocente(this);
			docente_disciplina_ministrada.get(i).setDisciplina(disc);
			i++;
		}
	}

	public void toDocenteDisciplinasMinistrada() {
		docente_disciplina_ministrada.clear();
		DocenteDisciplinaMinistradaVO docDiscVO;
		int ordem = 0;
		for (DisciplinaVO disciplinaVO : disciplinasMinistradas) {
			docDiscVO = new DocenteDisciplinaMinistradaVO();
			docDiscVO.setDocente(this);
			docDiscVO.setDisciplina(disciplinaVO);
			docDiscVO.setOrdem(ordem);
			docente_disciplina_ministrada.add(docDiscVO);
			ordem++;
		}
	}

	public double getHorasMinistradas() {
		horasMinistradas = 0;
		for (DisciplinaVO disc : getDisciplinasMinistradas()) {
			horasMinistradas += disc.getChSemanal();
		}
		return horasMinistradas;
	}
}
