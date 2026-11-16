package br.com.restaurante.controller;

import br.com.restaurante.model.Bebida;
import br.com.restaurante.model.Comida;
import br.com.restaurante.model.Produto;
import br.com.restaurante.repository.ProdutoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProdutoController {
    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto, Long> colunaId;
    @FXML private TableColumn<Produto, String> colunaNome;
    @FXML private TableColumn<Produto, String> colunaTipo;
    @FXML private TableColumn<Produto, String> colunaCategoria;
    @FXML private TableColumn<Produto, Double> colunaPreco;
    @FXML private TextField campoNome;
    @FXML private TextField campoDescricao;
    @FXML private TextField campoPreco;
    @FXML private TextField campoCategoria;
    @FXML private ComboBox<String> comboTipo;
    @FXML private Label mensagem;

    private final ProdutoRepository repository = new ProdutoRepository();
    private final ObservableList<Produto> produtos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colunaId.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getId()));
        colunaNome.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNome()));
        colunaTipo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTipo()));
        colunaCategoria.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCategoria()));
        colunaPreco.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getPreco()));
        comboTipo.setItems(FXCollections.observableArrayList("Comida", "Bebida"));
        comboTipo.getSelectionModel().selectFirst();
        tabelaProdutos.setItems(produtos);
        carregarProdutos();
    }

    @FXML
    private void cadastrarProduto() {
        String nome = campoNome.getText().trim();
        String descricao = campoDescricao.getText().trim();
        String categoria = campoCategoria.getText().trim();
        String tipo = comboTipo.getValue();

        if (nome.isBlank() || categoria.isBlank() || campoPreco.getText().isBlank() || tipo == null) {
            mostrarMensagem("Preencha nome, preço, categoria e tipo.", true);
            return;
        }

        final double preco;
        try {
            preco = Double.parseDouble(campoPreco.getText().trim().replace(',', '.'));
            if (!Double.isFinite(preco) || preco < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarMensagem("Informe um preço válido (ex.: 12.50).", true);
            return;
        }

        Produto produto = tipo.equals("Bebida")
                ? new Bebida(nome, descricao, preco, categoria)
                : new Comida(nome, descricao, preco, categoria);

        try {
            repository.salvar(produto);
            carregarProdutos();
            limparFormulario();
            mostrarMensagem("Produto cadastrado com sucesso!", false);
        } catch (Exception e) {
            mostrarMensagem("Erro ao salvar: " + e.getMessage(), true);
        }
    }

    @FXML
    private void atualizarTabela() {
        carregarProdutos();
        mostrarMensagem("Listagem atualizada.", false);
    }

    private void carregarProdutos() {
        try {
            produtos.setAll(repository.listarTodos());
        } catch (Exception e) {
            mostrarMensagem("Não foi possível consultar o banco: " + e.getMessage(), true);
        }
    }

    private void limparFormulario() {
        campoNome.clear();
        campoDescricao.clear();
        campoPreco.clear();
        campoCategoria.clear();
        comboTipo.getSelectionModel().selectFirst();
    }

    private void mostrarMensagem(String texto, boolean erro) {
        mensagem.setText(texto);
        mensagem.setStyle(erro ? "-fx-text-fill: #b91c1c;" : "-fx-text-fill: #15803d;");
    }
}
