package dao;

import java.util.List;

import org.hibernate.Query;

import vo.DocenteDisciplinaMinistradaVO;

public class DisciplinaMinistradaDAO extends DAO<DocenteDisciplinaMinistradaVO> {
	public static DisciplinaMinistradaDAO dao;

	public DisciplinaMinistradaDAO(Class classe) {
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
}
