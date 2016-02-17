package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import negocio.Docente;
import vo.DisciplinaVO;
import vo.DocenteVO;

@ManagedBean
@SessionScoped
public class Selecao {

	private ArrayList<DocenteVO> docentes;
	private ArrayList<DisciplinaVO> disciplinasSelecionadas = new ArrayList<DisciplinaVO>();
	private ArrayList<DocenteVO> listaSelecao = new ArrayList<DocenteVO>();
	private Set<DocenteVO> listaRemoverSelecao = new HashSet<DocenteVO>();
	private DisciplinaVO disc = new DisciplinaVO();

	public void seleciona(ArrayList<DocenteVO> docentes) {
		this.docentes = docentes;
		DocenteVO maiorLista = new DocenteVO();
		for (DocenteVO doc : this.docentes) {
			doc.getDisciplinasMinistradas().clear();
			if (doc.getDisciplinasInteresse().size() > maiorLista.getDisciplinasInteresse().size()) {
				maiorLista = doc;
			}
		}
		for (int i = 0; i < maiorLista.getDisciplinasInteresse().size() - 1; i++) {
			for (DocenteVO doc : this.docentes) {
				listaSelecao.clear();
				if (doc.getDisciplinasInteresse().size() <= 0) {
					continue;
				}
				if (doc.getHorasMinistradas() >= doc.getRegimeTrabalho()) {
					continue;
				}

				if (!doc.getDisciplinasInteresse().isEmpty() && doc.getDisciplinasInteresse().size() > i) {
					disc = doc.getDisciplinasInteresse().get(i);
				}
				if (disciplinasSelecionadas.contains(disc)) {
					continue;
				}
				for (DocenteVO doc2 : this.docentes) {
					if (!listaSelecao.contains(doc2)) {
						if (doc2.getDisciplinasInteresse().size() > 0 && doc2.getDisciplinasInteresse().size() > i) {
							if (doc2.getDisciplinasInteresse().get(i) == disc) {
								listaSelecao.add(doc2);
							}
						}
					}
				}
				if (listaSelecao.size() == 1 && listaSelecao.get(0).getDisciplinasInteresse().size() > i) {
					listaSelecao.get(0).getDisciplinasMinistradas().add(listaSelecao.get(0).getDisciplinasInteresse(i));
					listaSelecao.get(0).setContemplacao(doc.getContemplacao() + 1);
					disciplinasSelecionadas.add(disc);
				} else if (listaSelecao.size() > 1) {
					for (DocenteVO doc1 : listaSelecao) {
						for (DocenteVO doc2 : listaSelecao) {
							if (!listaRemoverSelecao.contains(doc2) | !listaRemoverSelecao.contains(doc1)) {
								if (doc1.getContratoNatureza().equals("Efetivo")
										&& (doc2.getContratoNatureza().equals("Substituto")
												|| doc2.getContratoNatureza().equals("Temporario"))) {
									listaRemoverSelecao.add(doc2);
								}
								if (doc2.getContratoNatureza().equals("Efetivo")
										&& (doc1.getContratoNatureza().equals("Substituto")
												|| doc1.getContratoNatureza().equals("Temporario"))) {
									listaRemoverSelecao.add(doc1);
								}
							}
						}
					}
					listaSelecao.removeAll(listaRemoverSelecao);
					listaRemoverSelecao.clear();
					if (listaSelecao.size() == 1 && listaSelecao.get(0).getDisciplinasInteresse().size() > i) {
						((DocenteVO) listaSelecao.get(0)).getDisciplinasMinistradas()
								.add(((DocenteVO) listaSelecao.get(0)).getDisciplinasInteresse(i));
						disciplinasSelecionadas.add(disc);
					} else {
						int igual = 0;
						for (int a = 0; a < listaSelecao.size() - 1; a++) {
							if (listaSelecao.get(a).getContemplacao() == listaSelecao.get(a + 1).getContemplacao()) {
								igual++;
							}
							if (listaSelecao.get(a).getContemplacao() > listaSelecao.get(a + 1).getContemplacao()) {
								listaRemoverSelecao.add(listaSelecao.get(a));
							}
							if (listaSelecao.get(a).getContemplacao() < listaSelecao.get(a + 1).getContemplacao()) {
								listaRemoverSelecao.add(listaSelecao.get(a + 1));
							}
						}
						listaSelecao.removeAll(listaRemoverSelecao);
						listaRemoverSelecao.clear();
						if (igual > 0 && listaSelecao.get(0).getDisciplinasInteresse().size() > i) {
							Random rand = new Random(0);
							int num = rand.nextInt(listaSelecao.size());
							listaSelecao.get(num).getDisciplinasMinistradas().add(doc.getDisciplinasInteresse().get(i));
							listaSelecao.get(num).setContemplacao(listaSelecao.get(num).getContemplacao() + 1);
						} else if (listaSelecao.get(0).getDisciplinasInteresse().size() > i) {
							listaSelecao.get(0).getDisciplinasMinistradas().add(doc.getDisciplinasInteresse().get(i));
							listaSelecao.get(0).setContemplacao(listaSelecao.get(0).getContemplacao() + 1);
						}
						disciplinasSelecionadas.add(disc);
					}
				}
			}
		}
		for (DocenteVO vo : docentes) {
			vo.toDocenteDisciplinasMinistrada();
		}
		Docente.update(docentes);
	}
}
