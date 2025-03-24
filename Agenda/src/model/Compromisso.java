package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Compromisso {

    private String descricao;
    private LocalDate data;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Compromisso(String descricao, LocalDate data) {
        this.descricao = descricao;
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return data.format(formatter) + " - " + descricao;
    }
}
