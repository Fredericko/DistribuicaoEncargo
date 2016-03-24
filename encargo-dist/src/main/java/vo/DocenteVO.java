package vo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OrderBy;

import enums.Cargos;
import enums.ContratoNatureza;

@Entity
@Table(name = "docente")
public class DocenteVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String sobrenome;
	@Column(unique = true)
	private String email;
	private String senha;
	@Column(name = "regime_trabalho")
	private double regimeTrabalho;
	@Column(name = "contrato_natureza")
	@Enumerated(EnumType.STRING)
	private ContratoNatureza contratoNatureza;
	@Column(name = "contrato_encerramento")
	private Date contratoEncerramento;
	@OneToMany(mappedBy = "docente", cascade = CascadeType.MERGE, orphanRemoval = true)
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@OrderBy(clause = "Ordem")
	private Set<DocenteDisciplinaInteresseVO> docenteDisciplinaInteresse = new TreeSet<DocenteDisciplinaInteresseVO>();
	@OneToMany(mappedBy = "docente", cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<DocenteDisciplinaMinistradaVO> docenteDisciplinaMinistrada = new HashSet<DocenteDisciplinaMinistradaVO>();
	@Enumerated(EnumType.STRING)
	private Cargos cargo;
	@Transient
	private int contemplacao;
	@Transient
	private double horasMinistradas;

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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public ContratoNatureza getContratoNatureza() {
		return contratoNatureza;
	}

	public void setContratoNatureza(ContratoNatureza contratoNatureza) {
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

	public Set<DocenteDisciplinaInteresseVO> getDocenteDisciplinasInteresse() {
		return docenteDisciplinaInteresse;
	}

	public DocenteDisciplinaInteresseVO getDocenteDisciplinaInteresse(int indice) {
		for (DocenteDisciplinaInteresseVO docDiscInt : docenteDisciplinaInteresse) {
			if (docDiscInt.getOrdem() == indice) {
				return docDiscInt;
			}
		}
		return null;
	}

	public void setDocenteDisciplinaInteresse(Set<DocenteDisciplinaInteresseVO> docente_disciplina_interesse) {
		this.docenteDisciplinaInteresse = docente_disciplina_interesse;
	}

	public void addDisciplinaInteresse(DisciplinaVO disciplina) {
		docenteDisciplinaInteresse.add(new DocenteDisciplinaInteresseVO(this, disciplina));
	}

	public void addDocenteDisciplinaInteresse(DocenteDisciplinaInteresseVO docDisInt) {
		docenteDisciplinaInteresse.add(docDisInt);
	}

	public Set<DocenteDisciplinaMinistradaVO> getDocenteDisciplinasMinistradas() {
		return docenteDisciplinaMinistrada;
	}

	public void setDocenteDisciplinaMinistrada(Set<DocenteDisciplinaMinistradaVO> docente_disciplina_ministrada) {
		this.docenteDisciplinaMinistrada = docente_disciplina_ministrada;
	}

	public void addDisciplinaMinistrada(DisciplinaVO disciplina) {
		docenteDisciplinaMinistrada.add(new DocenteDisciplinaMinistradaVO(this, disciplina));
	}

	public void setDisciplinasInteresse(List<DisciplinaVO> disciplinas) {
		int i = 0;
		docenteDisciplinaInteresse.clear();
		for (DisciplinaVO disc : disciplinas) {
			docenteDisciplinaInteresse.add(new DocenteDisciplinaInteresseVO(this, disc, i));
			i++;
		}
	}

	public double getHorasMinistradas() {
		horasMinistradas = 0;
		for (DocenteDisciplinaMinistradaVO disc : docenteDisciplinaMinistrada) {
			horasMinistradas += disc.getDisciplina().getChSemanal();
		}
		return horasMinistradas;
	}

	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	@Override
	public boolean equals(Object obj) {
		return ((DocenteVO) obj).getId() == this.getId();
	}

}
