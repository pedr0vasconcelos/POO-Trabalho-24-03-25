package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Compromisso;
import service.AgendaService;

import java.util.List;

public class AgendaApp extends Application {

    private AgendaService agendaService = new AgendaService();

    private TextField descricaoField = new TextField();
    private DatePicker dataPicker = new DatePicker();
    private TextField diasConsultaField = new TextField();
    private ListView<String> compromissosListView = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agenda de Compromissos");

        // Formulário para adicionar compromisso
        VBox addCompromissoBox = new VBox(10);
        addCompromissoBox.setPadding(new Insets(10));

        descricaoField.setPromptText("Descrição do compromisso");
        dataPicker.setPromptText("Data do compromisso");

        Button adicionarButton = new Button("Adicionar Compromisso");
        adicionarButton.setOnAction(e -> adicionarCompromisso());

        addCompromissoBox.getChildren().addAll(
                new Label("Descrição:"),
                descricaoField,
                new Label("Data:"),
                dataPicker,
                adicionarButton
        );

        // Consulta de compromissos
        VBox consultaBox = new VBox(10);
        consultaBox.setPadding(new Insets(10));

        diasConsultaField.setPromptText("Dias à frente");

        Button consultarButton = new Button("Consultar Compromissos");
        consultarButton.setOnAction(e -> consultarCompromissos());

        consultaBox.getChildren().addAll(
                new Label("Consultar compromissos futuros (n dias):"),
                diasConsultaField,
                consultarButton,
                new Label("Compromissos encontrados:"),
                compromissosListView
        );

        // Layout principal
        HBox root = new HBox(20);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(addCompromissoBox, consultaBox);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void adicionarCompromisso() {
        String descricao = descricaoField.getText();
        if (descricao.isEmpty()) {
            showAlert("Erro", "A descrição não pode ser vazia.");
            return;
        }

        if (dataPicker.getValue() == null) {
            showAlert("Erro", "A data deve ser selecionada.");
            return;
        }

        String dataStr = dataPicker.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        try {
            agendaService.adicionarCompromisso(descricao, dataStr);
            showAlert("Sucesso", "Compromisso adicionado com sucesso!");
            descricaoField.clear();
            dataPicker.setValue(null);
        } catch (Exception e) {
            showAlert("Erro", "Data inválida!");
        }
    }

    private void consultarCompromissos() {
        String diasStr = diasConsultaField.getText();

        if (diasStr.isEmpty()) {
            showAlert("Erro", "Informe a quantidade de dias!");
            return;
        }

        try {
            int dias = Integer.parseInt(diasStr);
            List<Compromisso> compromissos = agendaService.listarCompromissosFuturos(dias);

            compromissosListView.getItems().clear();
            if (compromissos.isEmpty()) {
                compromissosListView.getItems().add("Nenhum compromisso encontrado.");
            } else {
                for (Compromisso c : compromissos) {
                    compromissosListView.getItems().add(c.toString());
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Erro", "O campo de dias deve ser um número!");
        }
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
