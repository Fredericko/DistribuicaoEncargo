package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.DocenteDAO;
import enums.Cargos;
import enums.ContratoNatureza;
import vo.DocenteVO;

public class Test {
	
	private static String criptografar(String senha) {
		try {
			MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algoritmo.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		DocenteVO vo = new DocenteVO();
		vo.setNome("admin");
		vo.setSenha(criptografar("admin"));
		vo.setCargo(Cargos.CHEFE_DEPARTAMENTO);
		vo.setEmail("");
		vo.setContratoNatureza(ContratoNatureza.EFETIVO);
		
		DocenteDAO.getInstance().save(vo);
		
		/*ArrayList<DisciplinaVO> docDisIntList = new ArrayList<>();
		ArrayList<DisciplinaVO> docDisIntList2 = new ArrayList<>();
		ArrayList<DisciplinaVO> docDisIntList3 = new ArrayList<>();
		ArrayList<DisciplinaVO> docDisIntList4 = new ArrayList<>();
		
		DisciplinaVO disciplina = new DisciplinaVO();
		disciplina.setNomeDisciplina("Programação");
		disciplina.setTurno("Vespertiino");
		disciplina.setChSemanal(10);
		disciplina.setChTotal(120);
		disciplina.setArea(Areas.DESENVOLVIMENTO);
		
		DisciplinaVO disciplina2 = new DisciplinaVO();
		disciplina2.setNomeDisciplina("PCC");
		disciplina2.setTurno("Vespertiino");
		disciplina2.setChSemanal(10);
		disciplina2.setChTotal(120);
		disciplina2.setArea(Areas.NUCLEO_COMUM);
		
		DisciplinaVO disciplina3 = new DisciplinaVO();
		disciplina3.setNomeDisciplina("TCC");
		disciplina3.setTurno("Vespertiino");
		disciplina3.setChSemanal(10);
		disciplina3.setChTotal(120);
		disciplina3.setArea(Areas.NUCLEO_COMUM);
		
		DisciplinaVO disciplina4 = new DisciplinaVO();
		disciplina4.setNomeDisciplina("Estrutura de dados");
		disciplina4.setTurno("Vespertiino");
		disciplina4.setChSemanal(10);
		disciplina4.setChTotal(120);
		disciplina4.setArea(Areas.DESENVOLVIMENTO);
		
		DisciplinaVO disciplina5 = new DisciplinaVO();
		disciplina5.setNomeDisciplina("Comércio Eletrônico");
		disciplina5.setTurno("Vespertiino");
		disciplina5.setChSemanal(10);
		disciplina5.setChTotal(120);
		disciplina5.setArea(Areas.DESENVOLVIMENTO);

		DisciplinaVO disciplina6 = new DisciplinaVO();
		disciplina6.setNomeDisciplina("Ética");
		disciplina6.setTurno("Vespertiino");
		disciplina6.setChSemanal(10);
		disciplina6.setChTotal(120);
		disciplina6.setArea(Areas.NUCLEO_COMUM);

		DisciplinaDAO.getInstance().save(disciplina);
		DisciplinaDAO.getInstance().save(disciplina2);
		DisciplinaDAO.getInstance().save(disciplina3);
		DisciplinaDAO.getInstance().save(disciplina4);
		DisciplinaDAO.getInstance().save(disciplina5);
		DisciplinaDAO.getInstance().save(disciplina6);*/
		
		/*
		DocenteVO docente = new DocenteVO();
		docente.setNome("Fredericko");
		docente.setRegimeTrabalho(40);
		docente.setContratoNatureza(ContratoNatureza.EFETIVO);

		DocenteVO docente2 = new DocenteVO();
		docente2.setNome("Monica");
		docente2.setRegimeTrabalho(40);
		docente2.setContratoNatureza(ContratoNatureza.EFETIVO);
		
		DocenteVO docente3 = new DocenteVO();
		docente3.setNome("João Paulo");
		docente3.setRegimeTrabalho(40);
		docente3.setContratoNatureza(ContratoNatureza.SUBSTITUTO);
		
		DocenteVO docente4 = new DocenteVO();
		docente4.setNome("Docente");
		docente4.setRegimeTrabalho(40);
		docente4.setContratoNatureza(ContratoNatureza.TEMPORARIO);
		
		
		docDisIntList.add(disciplina);
		docDisIntList.add(disciplina3);
		docDisIntList.add(disciplina2);
		docDisIntList.add(disciplina5);

		docDisIntList2.add(disciplina);
		docDisIntList2.add(disciplina4);
		docDisIntList2.add(disciplina5);
		
		docDisIntList3.add(disciplina2);
		docDisIntList3.add(disciplina3);
		docDisIntList3.add(disciplina6);
		docDisIntList3.add(disciplina5);
		docDisIntList3.add(disciplina);

		docDisIntList4.add(disciplina6);
		docDisIntList4.add(disciplina3);
		docDisIntList4.add(disciplina);
		
		docente.setDisciplinasInteresse(docDisIntList);
		docente2.setDisciplinasInteresse(docDisIntList2);
		docente3.setDisciplinasInteresse(docDisIntList3);
		docente4.setDisciplinasInteresse(docDisIntList4);
		
		DocenteDAO.getInstance().saveOrUpdate(docente);
		DocenteDAO.getInstance().saveOrUpdate(docente2);
		DocenteDAO.getInstance().saveOrUpdate(docente3);
		DocenteDAO.getInstance().saveOrUpdate(docente4);
		
		DocenteDisciplinaInteresseVO docDiscInt = new DocenteDisciplinaInteresseVO();
		docDiscInt.setDocente(docente);
		docDiscInt.setDisciplina(disciplina);
		docDiscInt.setOrdem(0);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt);
		
		docDiscInt.setDocente(docente);
		docDiscInt.setDisciplina(disciplina2);
		docDiscInt.setOrdem(1);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt);
		
		docDiscInt.setDocente(docente);
		docDiscInt.setDisciplina(disciplina4);
		docDiscInt.setOrdem(2);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt);
		
		docDiscInt.setDocente(docente);
		docDiscInt.setDisciplina(disciplina5);
		docDiscInt.setOrdem(3);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt);
		
		docDiscInt.setDocente(docente);
		docDiscInt.setDisciplina(disciplina3);
		docDiscInt.setOrdem(4);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt);
		
		DocenteDisciplinaInteresseVO docDiscInt2 = new DocenteDisciplinaInteresseVO();
		docDiscInt2.setDocente(docente2);
		docDiscInt2.setDisciplina(disciplina);
		docDiscInt2.setOrdem(0);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt2);
		
		docDiscInt2 = new DocenteDisciplinaInteresseVO();
		docDiscInt2.setDocente(docente2);
		docDiscInt2.setDisciplina(disciplina3);
		docDiscInt2.setOrdem(1);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt2);
		
		docDiscInt2 = new DocenteDisciplinaInteresseVO();
		docDiscInt2.setDocente(docente2);
		docDiscInt2.setDisciplina(disciplina5);
		docDiscInt2.setOrdem(2);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt2);
		
		docDiscInt2 = new DocenteDisciplinaInteresseVO();
		docDiscInt2.setDocente(docente2);
		docDiscInt2.setDisciplina(disciplina4);
		docDiscInt2.setOrdem(3);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt2);
		
		docDiscInt2 = new DocenteDisciplinaInteresseVO();
		docDiscInt2.setDocente(docente2);
		docDiscInt2.setDisciplina(disciplina2);
		docDiscInt2.setOrdem(4);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt2);
		
		DocenteDisciplinaInteresseVO docDiscInt3 = new DocenteDisciplinaInteresseVO();
		docDiscInt3.setDocente(docente3);
		docDiscInt3.setDisciplina(disciplina6);
		docDiscInt3.setOrdem(0);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt3);
		
		docDiscInt2 = new DocenteDisciplinaInteresseVO();
		docDiscInt3.setDocente(docente3);
		docDiscInt3.setDisciplina(disciplina4);
		docDiscInt3.setOrdem(1);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt3);
		
		docDiscInt2 = new DocenteDisciplinaInteresseVO();
		docDiscInt3.setDocente(docente3);
		docDiscInt3.setDisciplina(disciplina3);
		docDiscInt3.setOrdem(2);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt3);
		
		docDiscInt2 = new DocenteDisciplinaInteresseVO();
		docDiscInt3.setDocente(docente3);
		docDiscInt3.setDisciplina(disciplina5);
		docDiscInt3.setOrdem(3);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt3);
		
		docDiscInt2 = new DocenteDisciplinaInteresseVO();
		docDiscInt3.setDocente(docente3);
		docDiscInt3.setDisciplina(disciplina);
		docDiscInt3.setOrdem(4);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt3);
		
		DocenteDisciplinaInteresseVO docDiscInt4 = new DocenteDisciplinaInteresseVO();
		docDiscInt4.setDocente(docente4);
		docDiscInt4.setDisciplina(disciplina6);
		docDiscInt4.setOrdem(0);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt4);
		
		docDiscInt4 = new DocenteDisciplinaInteresseVO();
		docDiscInt4.setDocente(docente4);
		docDiscInt4.setDisciplina(disciplina3);
		docDiscInt4.setOrdem(1);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt4);
		
		docDiscInt4 = new DocenteDisciplinaInteresseVO();
		docDiscInt4.setDocente(docente4);
		docDiscInt4.setDisciplina(disciplina4);
		docDiscInt4.setOrdem(2);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt4);
		
		docDiscInt4 = new DocenteDisciplinaInteresseVO();
		docDiscInt4.setDocente(docente4);
		docDiscInt4.setDisciplina(disciplina2);
		docDiscInt4.setOrdem(3);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt4);
		
		docDiscInt4 = new DocenteDisciplinaInteresseVO();
		docDiscInt4.setDocente(docente4);
		docDiscInt4.setDisciplina(disciplina5);
		docDiscInt4.setOrdem(4);
		DisciplinaInteresseDAO.getInstance().saveOrUpdate(docDiscInt4);*/
		
	}
}
