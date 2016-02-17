package dao;

import java.util.List;

import org.hibernate.Query;

import vo.DocenteDisciplinaInteresseVO;

public class DisciplinaInteresseDAO extends DAO<DocenteDisciplinaInteresseVO> {
	public static DisciplinaInteresseDAO dao;

	public DisciplinaInteresseDAO(Class classe) {
		super(classe);
	}

	public static DisciplinaInteresseDAO getInstance() {
		if (dao == null)
			dao = new DisciplinaInteresseDAO(DocenteDisciplinaInteresseVO.class);
		return dao;
	}

	public void inserirDisciplinaInteresse(List<DocenteDisciplinaInteresseVO> disciplinaInteresse){
		String hql = "INSERT INTO docente_disciplina_interesse(disciplina_id, docente_id, ordem)VALUES ";
		for (DocenteDisciplinaInteresseVO discInteresse : disciplinaInteresse) {
			if(disciplinaInteresse.get(disciplinaInteresse.size()-1) != discInteresse){
				hql += "('"+discInteresse.getDisciplina().getId()+"','"+discInteresse.getDocente().getId()+"',"+discInteresse.getOrdem()+"),";
			}else{
				hql += "('"+discInteresse.getDisciplina().getId()+"','"+discInteresse.getDocente().getId()+"',"+discInteresse.getOrdem()+")";
			}
		}
		Query query = getSession().createSQLQuery(hql);
		query.executeUpdate();
	}
	
}
