package dao;

import vo.DocenteVO;

public class DocenteDAO extends DAO<DocenteVO>{
	public static DocenteDAO dao;
	
	public DocenteDAO(Class classe) {
		super(classe);
	}
	
	public static DocenteDAO getInstance() {
		if (dao == null)
			dao = new DocenteDAO(DocenteVO.class);
		return dao;
	}
}
