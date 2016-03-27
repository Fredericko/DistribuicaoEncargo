package dao;

import java.util.List;

import vo.CursoVO;
import vo.DisciplinaVO;

public class CursoDAO extends DAO<CursoVO> {
	private static CursoDAO dao;
	
	public CursoDAO(Class classe) {
		super(classe);
	}
	
	public static CursoDAO getInstance() {
		if (dao == null)
			dao = new CursoDAO(CursoVO.class);
		return dao;
	}
	
	public List<DisciplinaVO> getDisciplinasPorCursoId(int id){
		String hql = "SELECT * FROM disciplina d LEFT JOIN curso_disciplina cd on d.id = cd.disciplinas_id LEFT JOIN curso cur on cd.cursovo_id = cur.id WHERE cur.id = :id";
		return getSession().createSQLQuery(hql).setInteger("id", id).list();
	}
	
}
