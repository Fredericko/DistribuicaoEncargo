package dao;

import vo.CursoVO;
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

}
