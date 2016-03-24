package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
		s.clear();
		s.update(vo);
		t.commit();
	}

	public void save(VO vo) {
		t = s.beginTransaction();
		s.clear();
		s.merge(vo);
		t.commit();
	}

	public void saveOrUpdate(VO vo) {
		t = s.beginTransaction();
		s.clear();
		s.saveOrUpdate(vo);
		t.commit();
	}

	public void save(List<VO> listaVo) {
		t = s.beginTransaction();
		s.clear();
		for (VO vo : listaVo) {
			s.saveOrUpdate(vo);
		}
		t.commit();
	}

	public void delete(VO vo) {
		t = s.beginTransaction();
		s.clear();
		s.delete(vo);
		t.commit();
	}

	public void delete(List<VO> listaVo) {
		t = s.beginTransaction();
		s.clear();
		for (VO vo : listaVo) {
			s.delete(vo);
		}
		t.commit();
	}

	public VO getById(int id) {
		return s.get(classe, id);
	}

	public List<VO> getAll() {
		List<VO> lista = (List<VO>) s.createCriteria(classe).list();
		return lista;
	}

}
