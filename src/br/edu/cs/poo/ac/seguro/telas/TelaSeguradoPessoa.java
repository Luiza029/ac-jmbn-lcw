package br.edu.cs.poo.ac.seguro.telas;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoPessoaMediator;

/**
 * Tela única de CRUD para SeguradoPessoa.
 * Busca, Inclui, Altera e Exclui usando o CPF como chave.
 */

public class TelaSeguradoPessoa extends JFrame {

    private final SeguradoPessoaMediator mediator = SeguradoPessoaMediator.getInstancia();
    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // segurado atualmente carregado na tela (resultado da última busca), usado para alterar
    private SeguradoPessoa seguradoCarregado;

    private JTextField txtCpf;
    private JTextField txtNome;
    private JTextField txtDataNascimento;
    private JTextField txtRenda;

    private JTextField txtLogradouro;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtCep;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JTextField txtPais;

    private JButton btnBuscar;
    private JButton btnIncluir;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnLimpar;

    public TelaSeguradoPessoa() {
        super("Cadastro de Segurado Pessoa");
        montarTela();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 560);
        setLocationRelativeTo(null);
    }

    private void montarTela() {
        JPanel painelCampos = new JPanel(new GridLayout(0, 2, 6, 6));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtCpf = new JTextField();
        txtNome = new JTextField();
        txtDataNascimento = new JTextField();
        txtRenda = new JTextField();
        txtLogradouro = new JTextField();
        txtNumero = new JTextField();
        txtComplemento = new JTextField();
        txtCep = new JTextField();
        txtCidade = new JTextField();
        txtEstado = new JTextField();
        txtPais = new JTextField();

        painelCampos.add(new JLabel("CPF:"));
        painelCampos.add(txtCpf);
        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Data de nascimento (dd/MM/yyyy):"));
        painelCampos.add(txtDataNascimento);
        painelCampos.add(new JLabel("Renda:"));
        painelCampos.add(txtRenda);

        painelCampos.add(new JLabel("Logradouro:"));
        painelCampos.add(txtLogradouro);
        painelCampos.add(new JLabel("Número:"));
        painelCampos.add(txtNumero);
        painelCampos.add(new JLabel("Complemento:"));
        painelCampos.add(txtComplemento);
        painelCampos.add(new JLabel("CEP:"));
        painelCampos.add(txtCep);
        painelCampos.add(new JLabel("Cidade:"));
        painelCampos.add(txtCidade);
        painelCampos.add(new JLabel("Estado (UF):"));
        painelCampos.add(txtEstado);
        painelCampos.add(new JLabel("País:"));
        painelCampos.add(txtPais);

        btnBuscar = new JButton("Buscar");
        btnIncluir = new JButton("Incluir");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnBuscar);
        painelBotoes.add(btnIncluir);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        btnBuscar.addActionListener(e -> buscar());
        btnIncluir.addActionListener(e -> incluir());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limparCampos());

        setLayout(new BorderLayout());
        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void buscar() {
        String cpf = txtCpf.getText().trim();
        SeguradoPessoa seg = mediator.buscarSeguradoPessoa(cpf);
        if (seg == null) {
            seguradoCarregado = null;
            JOptionPane.showMessageDialog(this, "Segurado pessoa não encontrado para o CPF informado.");
            return;
        }
        seguradoCarregado = seg;
        preencherCamposComSegurado(seg);
        JOptionPane.showMessageDialog(this, "Segurado encontrado.");
    }

    private void incluir() {
        LocalDate dataNascimento = parseData(txtDataNascimento.getText().trim());
        if (dataNascimento == null && !txtDataNascimento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        Endereco endereco = montarEndereco();
        double renda = parseDouble(txtRenda.getText().trim());

        SeguradoPessoa novo = new SeguradoPessoa(
                txtNome.getText().trim(),
                endereco,
                dataNascimento,
                BigDecimal.ZERO,
                txtCpf.getText().trim(),
                renda
        );

        String erro = mediator.incluirSeguradoPessoa(novo);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Erro ao incluir", JOptionPane.ERROR_MESSAGE);
            return;
        }
        seguradoCarregado = novo;
        JOptionPane.showMessageDialog(this, "Segurado pessoa incluído com sucesso.");
    }

    private void alterar() {
        if (seguradoCarregado == null) {
            JOptionPane.showMessageDialog(this, "Busque um segurado pelo CPF antes de alterar.");
            return;
        }

        LocalDate dataNascimento = parseData(txtDataNascimento.getText().trim());
        if (dataNascimento == null && !txtDataNascimento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        seguradoCarregado.setNome(txtNome.getText().trim());
        seguradoCarregado.setEndereco(montarEndereco());
        seguradoCarregado.setDataNascimento(dataNascimento);
        seguradoCarregado.setRenda(parseDouble(txtRenda.getText().trim()));

        String erro = mediator.alterarSeguradoPessoa(seguradoCarregado);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Segurado pessoa alterado com sucesso.");
    }

    private void excluir() {
        String cpf = txtCpf.getText().trim();
        String erro = mediator.excluirSeguradoPessoa(cpf);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Erro ao excluir", JOptionPane.ERROR_MESSAGE);
            return;
        }
        seguradoCarregado = null;
        limparCampos();
        JOptionPane.showMessageDialog(this, "Segurado pessoa excluído com sucesso.");
    }

    private void limparCampos() {
        seguradoCarregado = null;
        txtCpf.setText("");
        txtNome.setText("");
        txtDataNascimento.setText("");
        txtRenda.setText("");
        txtLogradouro.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtCep.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        txtPais.setText("");
    }

    private void preencherCamposComSegurado(SeguradoPessoa seg) {
        txtNome.setText(seg.getNome());
        txtDataNascimento.setText(seg.getDataNascimento() == null ? "" : seg.getDataNascimento().format(formatoData));
        txtRenda.setText(String.valueOf(seg.getRenda()));

        Endereco end = seg.getEndereco();
        if (end != null) {
            txtLogradouro.setText(valorOuVazio(end.getLogradouro()));
            txtNumero.setText(valorOuVazio(end.getNumero()));
            txtComplemento.setText(valorOuVazio(end.getComplemento()));
            txtCep.setText(valorOuVazio(end.getCep()));
            txtCidade.setText(valorOuVazio(end.getCidade()));
            txtEstado.setText(valorOuVazio(end.getEstado()));
            txtPais.setText(valorOuVazio(end.getPais()));
        }
    }

    private Endereco montarEndereco() {
        return new Endereco(
                txtLogradouro.getText().trim(),
                txtCep.getText().trim(),
                txtNumero.getText().trim(),
                txtComplemento.getText().trim(),
                txtPais.getText().trim(),
                txtEstado.getText().trim(),
                txtCidade.getText().trim()
        );
    }

    private LocalDate parseData(String texto) {
        if (texto.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(texto, formatoData);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private double parseDouble(String texto) {
        if (texto.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(texto.replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String valorOuVazio(String texto) {
        return texto == null ? "" : texto;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaSeguradoPessoa().setVisible(true));
    }
}