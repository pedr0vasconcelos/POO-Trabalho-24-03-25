package service;

import model.Compromisso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AgendaService {

    private List<Compromisso> compromissos;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AgendaService() {
        this.compromissos = new ArrayList<>();
    }

    public void adicionarCompromisso(String descricao, String dataStr) throws Exception {
        LocalDate data = LocalDate.parse(dataStr, formatter);
        Compromisso compromisso = new Compromisso(descricao, data);
        compromissos.add(compromisso);
    }

    public List<Compromisso> listarCompromissosFuturos(int dias) {
        LocalDate hoje = LocalDate.now();
        LocalDate limite = hoje.plusDays(dias);

        List<Compromisso> futuros = new ArrayList<>();
        for (Compromisso compromisso : compromissos) {
            if (!compromisso.getData().isBefore(hoje) && !compromisso.getData().isAfter(limite)) {
                futuros.add(compromisso);
            }
        }

        return futuros;
    }
}
