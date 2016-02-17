package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DAO<VO> {

	private Class classe;
	private Session s;
	private Transaction t;

	public DAO(Class classe) {
		s = HibernateUtil.getSession().openSession();
		this.classe = classe;
	}

	public Session getSession() {
		return s;
	}

	public void save(VO vo) {
		t = s.beginTransaction();
		s.save(vo);
		t.commit();
		s.clear();
	}

	public void save(List<VO> listaVo) {
		t = s.beginTransaction();
		for (VO vo : listaVo) {
			s.merge(vo);
		}
		t.commit();
		s.clear();
	}

	public void delete(VO vo) {
		t = s.beginTransaction();
		s.delete(vo);
		t.commit();
		s.clear();
	}

	public void delete(List<VO> listaVo) {
		t = s.beginTransaction();
		for (VO vo : listaVo) {
			s.delete(vo);
		}
		t.commit();
		s.clear();
	}

	public VO getById(long id) {
		return (VO) s.get(classe, id);
	}

	public List<VO> getAll() {
		return s.createCriteria(classe).list();
	}

}
