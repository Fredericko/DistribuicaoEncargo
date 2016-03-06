package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import util.HibernateUtil;

public class DAO<VO> {

	private Class<VO> classe;
	private Session s;
	private Transaction t;

	public DAO(Class<VO> classe) {
		s = HibernateUtil.getSession().openSession();
		this.classe = classe;
	}

	public Session getSession() {
		return s;
	}
	
	public void update(VO vo){
		t = s.beginTransaction();
		s.update(vo);
		t.commit();
		s.clear();
	}

	public void save(VO vo) {
		t = s.beginTransaction();
		s.merge(vo);
		t.commit();
		s.clear();
	}

	public void saveOrUpdate(VO vo) {
		t = s.beginTransaction();
		s.saveOrUpdate(vo);
		t.commit();
		s.clear();
	}

	public void save(List<VO> listaVo) {
		t = s.beginTransaction();
		for (VO vo : listaVo) {
			s.saveOrUpdate(vo);
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

	public VO getById(int id) {
		return s.get(classe, id);
	}

	public List<VO> getAll() {
		return (List<VO>) s.createCriteria(classe).list();
	}

}
