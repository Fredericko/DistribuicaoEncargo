package dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

import vo.DisciplinaVO;

public class DisciplinaDAO extends DAO<DisciplinaVO>{
	public static DisciplinaDAO dao;
	
	public DisciplinaDAO(Class classe) {
		super(classe);
	}

	public static DisciplinaDAO getInstance() {
		if (dao == null)
			dao = new DisciplinaDAO(DisciplinaVO.class);
		return dao;
	}

	public List<DisciplinaVO> getDisciplinasDisponiveis() {
		String hql = "FROM DisciplinaVO WHERE disponivel = true";
		return getInstance().getSession().createQuery(hql).list();
	}
	
}
