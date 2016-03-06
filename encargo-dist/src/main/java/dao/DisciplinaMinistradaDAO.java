package dao;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Order;
import org.hibernate.type.IntegerType;

import vo.DisciplinaVO;
import vo.DocenteDisciplinaMinistradaVO;

@SuppressWarnings("unchecked")
public class DisciplinaMinistradaDAO extends DAO<DocenteDisciplinaMinistradaVO> {
	public static DisciplinaMinistradaDAO dao;

	public DisciplinaMinistradaDAO(Class<DocenteDisciplinaMinistradaVO> classe) {
		super(classe);
	}

	public static DisciplinaMinistradaDAO getInstance() {
		if (dao == null)
			dao = new DisciplinaMinistradaDAO(DocenteDisciplinaMinistradaVO.class);
		return dao;
	}

	public Object getDisciplinaMinistrada(DocenteDisciplinaMinistradaVO disciplinaMinistrada) {
		String hql = "SELECT * FROM docente_disciplina_ministrada WHERE docente_id = :docenteId AND disciplina_id = :disciplinaId";
		return getSession().createSQLQuery(hql).setLong("docenteId", disciplinaMinistrada.getDocente().getId())
				.setLong("disciplinaId", disciplinaMinistrada.getDisciplina().getId()).uniqueResult();
	}

	public List<DocenteDisciplinaMinistradaVO> getDisciplinaMinistradaPorSemestre(
			DocenteDisciplinaMinistradaVO disciplinaMinistrada, int ano, int semestre) {
		String hql = "SELECT * FROM docente_disciplina_ministrada WHERE docente_id = :docenteId AND disciplina_id = :disciplinaId AND ano = :ano AND semestre = :semestre";
		return getSession().createSQLQuery(hql).setDouble("docenteId", disciplinaMinistrada.getDocente().getId())
				.setDouble("disciplinaId", disciplinaMinistrada.getDisciplina().getId()).setInteger("ano", ano)
				.setInteger("semestre", semestre).list();
	}

	public Object getDisciplinaMinistradaPorSemestre(DocenteDisciplinaMinistradaVO disciplinaMinistrada) {
		return getSession().load("docente_disciplina_ministrada", disciplinaMinistrada.getId());
	}

	public void saveOrUpdate(Set<DocenteDisciplinaMinistradaVO> docenteDisciplinasMinistradas) {
		for (DocenteDisciplinaMinistradaVO vo : docenteDisciplinasMinistradas) {
			if (getDisciplinaMinistradaPorSemestre(vo, vo.getAno(), vo.getSemestre()).size() <= 0) {
				String hql = "INSERT INTO docente_disciplina_ministrada(docente_id, disciplina_id, ano, semestre) VALUES  (:docenteId, :disciplinaId, :ano, :semestre)";
				getSession().createSQLQuery(hql).setDouble("docenteId", vo.getDocente().getId())
						.setDouble("disciplinaId", vo.getDisciplina().getId()).setInteger("ano", vo.getAno())
						.setInteger("semestre", vo.getSemestre()).executeUpdate();
			} else {
				update(vo);
			}
		}
	}

	public List<DocenteDisciplinaMinistradaVO> getDisciplinasMinistradasMaisRecente(int docenteId) {
		String hql = "FROM DocenteDisciplinaMinistradaVO WHERE docente_id = :docenteId AND ano IN (SELECT MAX(ano) FROM DocenteDisciplinaMinistradaVO) AND semestre IN (SELECT MAX(semestre) FROM DocenteDisciplinaMinistradaVO WHERE ano = (SELECT MAX(ano) FROM DocenteDisciplinaMinistradaVO))";
		return getSession().createQuery(hql).setInteger("docenteId", docenteId).list();
	}

	public List<DocenteDisciplinaMinistradaVO> getAllOrdenadoPorAnoSemestre() {
		String hql = "FROM DocenteDisciplinaMinistradaVO ORDER BY ano, semestre ASC";
		return (List<DocenteDisciplinaMinistradaVO>) getInstance().getSession().createQuery(hql).list();
	}

	public Object[] getAnoSemestreAtual() {
		String hql = "SELECT MAX(ano), MAX(semestre) FROM DocenteDisciplinaMinistradaVO WHERE ano = (SELECT max(ano) FROM DocenteDisciplinaMinistradaVO) AND semestre = (SELECT max(semestre) FROM DocenteDisciplinaMinistradaVO WHERE ano = (SELECT MAX(ano) FROM DocenteDisciplinaMinistradaVO))";
		return (Object[]) getInstance().getSession().createQuery(hql).uniqueResult();
	}

	public static List<DisciplinaVO> getDisciplinasNaoSelecionadas() {
		String hql = "FROM DisciplinaVO WHERE id NOT IN (SELECT disciplina FROM DocenteDisciplinaMinistradaVO WHERE ano = (SELECT max(ano) FROM DocenteDisciplinaMinistradaVO) AND semestre = (SELECT max(semestre) FROM DocenteDisciplinaMinistradaVO WHERE ano = (SELECT MAX(ano) FROM DocenteDisciplinaMinistradaVO)))";
		return getInstance().getSession().createQuery(hql).list();
	}

}
