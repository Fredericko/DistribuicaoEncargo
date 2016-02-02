package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DAO<VO> {
	private Class classe;

	public DAO(Class classe) {
		this.classe = classe;
	}

	public void save(VO vo) {
		Session s = HibernateUtil.getSession().openSession();
		Transaction t = s.beginTransaction();
		s.merge(vo);
		t.commit();
		s.clear();
	}

	public void delete(VO vo) {
		Session s = HibernateUtil.getSession().openSession();
		Transaction t = s.beginTransaction();
		s.delete(vo);
		t.commit();
		s.clear();
	}

	public VO getById(long id) {
		return (VO) HibernateUtil.getSession().openSession().get(classe, id);
	}

	public List<VO> getAll() {
		return HibernateUtil.getSession().openSession().createCriteria(classe).list();
	}

}
