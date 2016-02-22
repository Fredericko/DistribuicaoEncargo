package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import vo.DocenteDisciplinaMinistradaVO;

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

	public void inserirDisciplinaMinistradas(List<DocenteDisciplinaMinistradaVO> disciplinaMinistradas) {
		String hql = "INSERT INTO docente_disciplina_ministrada(disciplina_id, docente_id, ordem)VALUES ";
		for (DocenteDisciplinaMinistradaVO discMinistrada : disciplinaMinistradas) {
			if (disciplinaMinistradas.get(disciplinaMinistradas.size() - 1) != discMinistrada) {
				hql += "('"+ discMinistrada.getDisciplina().getId() + "','" + discMinistrada.getDocente().getId() + "',"+discMinistrada.getOrdem()+" ),";
			} else {
				hql += "('"+ discMinistrada.getDisciplina().getId() + "','" + discMinistrada.getDocente().getId() + "',"+discMinistrada.getOrdem()+" )";
			}
		}
		Query query = getSession().createSQLQuery(hql);
		query.executeUpdate();
	}

	public Object getDisciplinaMinistrada(DocenteDisciplinaMinistradaVO disciplinaMinistrada) {
		String hql = "SELECT * FROM docente_disciplina_ministrada WHERE docente_id = :docenteId AND disciplina_id = :disciplinaId";
		return getSession().createSQLQuery(hql).setLong("docenteId", disciplinaMinistrada.getDocente().getId())
		.setLong("disciplinaId", disciplinaMinistrada.getDisciplina().getId())
        .uniqueResult();
	}

	public void saveOrUpdate(Set<DocenteDisciplinaMinistradaVO> docenteDisciplinasMinistradas) {
		for(DocenteDisciplinaMinistradaVO disc : docenteDisciplinasMinistradas){
			if(getDisciplinaMinistrada(disc) == null){
				save(disc);
			}
		}
	}
}
