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

	public static DocenteVO logar(String usuario, String senha){
		String hql = "FROM DocenteVO WHERE nome = :nome AND senha = :senha";
		return (DocenteVO) getInstance().getSession().createQuery(hql).setString("nome", usuario).setString("senha", senha).uniqueResult();
	}
	
}
