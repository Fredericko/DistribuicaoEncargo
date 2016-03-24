package dao;

import vo.DocenteVO;

public class PerfilDAO extends DAO<DocenteVO>{
	public static PerfilDAO dao;
	
	public PerfilDAO(Class classe) {
		super(classe);
	}
	
	public static PerfilDAO getInstance() {
		if (dao == null)
			dao = new PerfilDAO(DocenteVO.class);
		return dao;
	}

	public static DocenteVO logar(String usuario, String senha){
		String hql = "FROM DocenteVO WHERE nome = :nome AND senha = :senha";
		return (DocenteVO) getInstance().getSession().createQuery(hql).setString("nome", usuario).setString("senha", senha).uniqueResult();
	}
	
}
