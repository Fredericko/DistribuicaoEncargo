package dao;

import vo.CursoVO;

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
	
}
