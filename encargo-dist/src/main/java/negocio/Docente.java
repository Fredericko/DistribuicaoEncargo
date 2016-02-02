package negocio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.DocenteDAO;
import vo.DocenteVO;

@ManagedBean
@SessionScoped
public class Docente {

	public static void update(DocenteVO vo) {
		DocenteDAO.getInstance().save(vo);
	}

	public void delete(DocenteVO vo) {
		DocenteDAO.getInstance().delete(vo);
	}
	
	public void getAll(){
		DocenteDAO.getInstance().getAll();
	}
	
	
}
