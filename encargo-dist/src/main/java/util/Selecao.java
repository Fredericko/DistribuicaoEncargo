package util;

import java.util.ArrayList;
import java.util.Random;
import negocio.Docente;

import vo.DisciplinaVO;
import vo.DocenteVO;

public class Selecao {

    private ArrayList<DocenteVO> docentes;
    private ArrayList<DisciplinaVO> disciplinasSelecionadas;
    private ArrayList<DocenteVO> listaSelecao;

    public void seleciona(ArrayList<DocenteVO> docentes) {
        this.docentes = docentes;
        disciplinasSelecionadas = new ArrayList<>();
        int i = 0;
        for (DocenteVO doc : this.docentes) {
            listaSelecao.clear();
            if (doc.getHorasMinistradas() >= doc.getRegimeTrabalho()) {
                continue;
            }
            DisciplinaVO disc = doc.getDisciplinasInteresse().get(i);
            if (disciplinasSelecionadas.contains(disc)) {
                continue;
            }
            listaSelecao.add(doc);
            for (DocenteVO doc2 : this.docentes) {
                if (doc != doc2) {
                    if (doc2.getDisciplinasInteresse() == disc) {
                        listaSelecao.add(doc2);
                        doc2.setContemplacao(doc2.getContemplacao() + 1);
                    }
                }
            }
            if (listaSelecao.size() == 1) {
                doc.getDisciplinasMinistradas().add(doc.getDisciplinasPreferencia(i));
                disciplinasSelecionadas.add(disc);
            } else {
                for (DocenteVO doc1 : listaSelecao) {
                    for (DocenteVO doc2 : listaSelecao) {
                        if (doc1.getContratoNatureza().equals("Efetivo") && (doc2.getContratoNatureza().equals("Substituto") || doc2.getContratoNatureza().equals("Temporario"))) {
                            listaSelecao.remove(doc2);
                        } else if (doc2.getContratoNatureza().equals("Efetivo") && (doc1.getContratoNatureza().equals("Substituto") || doc1.getContratoNatureza().equals("Temporario"))) {
                            listaSelecao.remove(doc1);
                            break;
                        }
                    }
                }
                if (listaSelecao.size() == 1) {
                    ((DocenteVO) listaSelecao.get(0)).getDisciplinasMinistradas().add(((DocenteVO) listaSelecao.get(0)).getDisciplinasPreferencia(i));
                    disciplinasSelecionadas.add(disc);
                } else {
                    int igual = 0;
                    for (int a = 0; a < listaSelecao.size() - 1; a++) {
                        if (listaSelecao.get(a).getContemplacao() == listaSelecao.get(a + 1).getContemplacao()) {
                            igual++;
                            continue;
                        }
                        if (listaSelecao.get(a).getContemplacao() > listaSelecao.get(a + 1).getContemplacao()) {
                            listaSelecao.remove(listaSelecao.get(a));
                        } else if (listaSelecao.get(a).getContemplacao() < listaSelecao.get(a + 1).getContemplacao()) {
                            listaSelecao.remove(listaSelecao.get(a + 1));
                        }
                    }
                    if (igual > 0) {
                        Random rand = new Random(0);
                        int num = rand.nextInt(listaSelecao.size());
                        listaSelecao.get(num).getDisciplinasMinistradas().add(doc.getDisciplinasInteresse().get(i));
                    } else {
                        listaSelecao.get(0).getDisciplinasMinistradas().add(doc.getDisciplinasInteresse().get(i));
                    }
                    disciplinasSelecionadas.add(disc);
                }
            }
            i++;
        }
        Docente.update(docentes);
    }

}
