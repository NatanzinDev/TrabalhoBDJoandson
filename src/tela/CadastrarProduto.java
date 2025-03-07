package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import banco.FabricaConexao;
import dominio.Produto;
import javax.swing.JComboBox;

public class CadastrarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private Produto ProdutoEdicao;
	private JButton btnNewButtonCadastrarProduto;
	private JTextField textFieldPreco;
	private JTextField textFieldValidade;
	private JList listarProdutos;
	private JPanel panelListar;
	private JPanel panelCadastrar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarProduto frame = new CadastrarProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastrarProduto() throws ClassNotFoundException, SQLException {
		setBackground(new Color(0, 128, 0));
		setTitle("Cadastro do Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 508);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCadastrar = new JPanel();
		panelCadastrar.setBackground(new Color(192, 192, 192));
		panelCadastrar.setBorder(
				new TitledBorder(null, "Cadastrar Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCadastrar.setBounds(10, 10, 224, 429);
		contentPane.add(panelCadastrar);
		panelCadastrar.setLayout(null);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(54, 70, 125, 29);
		panelCadastrar.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(54, 54, 46, 14);
		panelCadastrar.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Preco");
		lblNewLabel_1.setBounds(54, 109, 59, 14);
		panelCadastrar.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Validade");
		lblNewLabel_2.setBounds(54, 169, 59, 14);
		panelCadastrar.add(lblNewLabel_2);

		btnNewButtonCadastrarProduto = new JButton("Cadastrar Produto");
		btnNewButtonCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrarProduto();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonCadastrarProduto.setBounds(43, 322, 150, 46);
		panelCadastrar.add(btnNewButtonCadastrarProduto);

		textFieldPreco = new JTextField();
		textFieldPreco.setBounds(54, 130, 125, 29);
		panelCadastrar.add(textFieldPreco);
		textFieldPreco.setColumns(10);

		textFieldValidade = new JTextField();
		textFieldValidade.setColumns(10);
		textFieldValidade.setBounds(54, 186, 125, 29);
		panelCadastrar.add(textFieldValidade);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(43, 253, 150, 21);
		panelCadastrar.add(comboBox);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tipo de estoque");
		lblNewLabel_2_1.setBounds(54, 229, 103, 14);
		panelCadastrar.add(lblNewLabel_2_1);

		panelListar = new JPanel();
		panelListar.setBackground(new Color(192, 192, 192));
		panelListar.setBorder(
				new TitledBorder(null, "Listagem de Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelListar.setBounds(244, 38, 484, 359);
		contentPane.add(panelListar);
		panelListar.setLayout(null);

		listarProdutos = new JList();
		listarProdutos.setBounds(185, 22, 272, 314);
		panelListar.add(listarProdutos);

		JButton btnNewButtonExibir = new JButton("Exibir Produto");
		btnNewButtonExibir.setBounds(10, 120, 150, 23);
		panelListar.add(btnNewButtonExibir);

		JButton btnNewButtonEditar = new JButton("Editar Produto");
		btnNewButtonEditar.setBounds(10, 176, 150, 23);
		panelListar.add(btnNewButtonEditar);

		JButton btnNewButtonRemover = new JButton("Remover Produto");
		btnNewButtonRemover.setBounds(10, 225, 150, 23);
		panelListar.add(btnNewButtonRemover);
		btnNewButtonRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					removerDados();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarEdicaoProduto();
			}
		});
		btnNewButtonExibir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Produto produtoSelecionado = (Produto) listarProdutos.getSelectedValue();

				if (listarProdutos.getSelectedIndex() == -1) {
					exibirMensagemErro("Selecione um Produto");
					return;
				}

				String msg = "Nome: " + produtoSelecionado.getNome() + "\nPreço : " + produtoSelecionado.getPreco()
						+ "\nValidade: " + produtoSelecionado.getValidade();

				ExibirMensagem(msg);

			}
		});

		atualizarListagemProdutos();

	}

	protected void removerDados() throws ClassNotFoundException, SQLException {

		if (listarProdutos.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione um Produto");
		}

		ProdutoEdicao = (Produto) listarProdutos.getSelectedValue();

		Connection conexao = FabricaConexao.criarConexao();

		String sql = "DELETE FROM PRODUTO WHERE ID = ?";

		PreparedStatement comando = conexao.prepareStatement(sql);

		comando.setInt(1, ProdutoEdicao.getId());
		comando.executeUpdate();

		ExibirMensagem("Produto Removido");

		atualizarListagemProdutos();

		comando.close();
		conexao.close();

	}

	protected void iniciarEdicaoProduto() {

		if (listarProdutos.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione um Produto");
		}

		ProdutoEdicao = (Produto) listarProdutos.getSelectedValue();
		textFieldNome.setText(ProdutoEdicao.getNome());
		textFieldPreco.setText(ProdutoEdicao.getPreco());
		textFieldValidade.setText(ProdutoEdicao.getValidade());

		btnNewButtonCadastrarProduto.setText("Editar Produto");

	}

	protected void ExibirMensagem(String msg) {

		JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

	}

	private void atualizarListagemProdutos() throws ClassNotFoundException, SQLException {

		Connection conexao = FabricaConexao.criarConexao();

		String sql = "SELECT * FROM PRODUTO";

		PreparedStatement comando = conexao.prepareStatement(sql);

		ResultSet resultado = comando.executeQuery();

		List<Produto> produtosCadastrados = new ArrayList<>();

		while (resultado.next()) {
			Produto a = new Produto();

			a.setId(resultado.getInt("id"));
			a.setNome(resultado.getString("nome"));
			a.setPreco(resultado.getString("preco"));
			a.setValidade(resultado.getString("validade"));

			produtosCadastrados.add(a);

		}

		DefaultListModel<Produto> modelo = new DefaultListModel<>();

		for (Produto a : produtosCadastrados) {
			modelo.addElement(a);
		}

		listarProdutos.setModel(modelo);

		comando.close();
		conexao.close();

	}

	protected void cadastrarProduto() throws ClassNotFoundException, SQLException {

		if (textFieldNome.getText() == null || textFieldNome.getText().isEmpty()) {
			exibirMensagemErro("Nome não pode ser vazio");
			return;
		}

		if (textFieldPreco.getText() == null || textFieldPreco.getText().isEmpty()) {
			exibirMensagemErro("Código não pode ser vazio");
			return;
		}

		if (textFieldValidade.getText() == null || textFieldValidade.getText().isEmpty()) {
			exibirMensagemErro("Validade não pode ser vazio");
			return;
		}

		if (btnNewButtonCadastrarProduto.getText().equals("Cadastrar Produto")) {

			Connection conexao = FabricaConexao.criarConexao();

			String sql = "INSERT INTO PRODUTO (nome, preco, validade) VALUES (?, ?, ?)";

			Produto a = new Produto();
			a.setNome(textFieldNome.getText());
			a.setPreco(textFieldPreco.getText());
			a.setValidade(textFieldValidade.getText());

			PreparedStatement comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			comando.setString(1, a.getNome());
			comando.setString(2, a.getPreco());
			comando.setString(3, a.getValidade());
			comando.executeUpdate();

			ResultSet rs = comando.getGeneratedKeys();
			if (rs.next()) {
				a.setId(rs.getInt(1));
			}

			comando.close();
			conexao.close();

			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (btnNewButtonCadastrarProduto.getText().equals("Editar Produto")) {

			Connection conexao = FabricaConexao.criarConexao();

			ProdutoEdicao.setNome(textFieldNome.getText());
			ProdutoEdicao.setPreco(textFieldPreco.getText());
			ProdutoEdicao.setValidade(textFieldValidade.getText());

			String sql = "UPDATE PRODUTO SET nome = ?, preco = ?, validade = ? WHERE id = ?";

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, ProdutoEdicao.getNome());
			comando.setString(2, ProdutoEdicao.getPreco());
			comando.setString(3, ProdutoEdicao.getValidade());
			comando.setInt(4, ProdutoEdicao.getId());

			comando.executeUpdate();

			ExibirMensagem("Dados Alterados");

			comando.close();
			conexao.close();

			ProdutoEdicao = null;
		}

		atualizarListagemProdutos();

		textFieldNome.setText("");
		textFieldPreco.setText("");
		textFieldValidade.setText("");
	}

	protected void exibirMensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
