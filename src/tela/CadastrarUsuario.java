package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import banco.UsuarioDao;
import util.CriptografiaUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CadastrarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarUsuario frame = new CadastrarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastrarUsuario() {
		setTitle("Cadastrar Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastrar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(66, 21, 409, 343);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(99, 94, 224, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(102, 71, 45, 13);
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(99, 153, 224, 19);
		panel.add(textField_1);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(99, 130, 45, 13);
		panel.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(99, 191, 45, 13);
		panel.add(lblSenha);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrar();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(102, 283, 137, 21);
		panel.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 214, 224, 19);
		panel.add(passwordField);
	}

	protected void cadastrar() throws ClassNotFoundException, SQLException {
		String nome = textField.getText();
		String email = textField_1.getText();
		String senha = new String(passwordField.getPassword());
		String senhaCriptografada = CriptografiaUtils.criptografarMD5(senha);

		if (nome == null || nome.isEmpty()) {
			exibirMensagemErro("Nome não pode ser vazio");
			return;
		}

		if (email == null || email.isEmpty()) {
			exibirMensagemErro("Email não pode ser vazio");
			return;
		}

		if (senha == null || senha.isEmpty()) {
			exibirMensagemErro("Senha não pode ser vazio");
			return;
		}
		
		UsuarioDao dao = new UsuarioDao();
		boolean a  = dao.cadastrarUsuario(nome, senhaCriptografada, email);
		if(a) {
			Principal ca = null;
			ca = new Principal();
			ca.setLocationRelativeTo(null);
			ca.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			ca.setVisible(true);
		}
	}

	private void exibirMensagemErro(String string) {
		JOptionPane.showMessageDialog(null, string);
		
	}
	

}
