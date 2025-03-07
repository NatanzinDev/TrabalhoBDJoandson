package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import banco.UsuarioDao;
import dominio.Usuario;
import util.CriptografiaUtils;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Tela de Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(58, 44, 302, 330);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logar();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(64, 204, 114, 21);
		panel.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(64, 75, 167, 19);
		panel.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(64, 57, 45, 13);
		panel.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(64, 120, 45, 13);
		panel.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(64, 146, 167, 19);
		panel.add(passwordField);
	}
	
	protected void logar() throws ClassNotFoundException, SQLException {
		String email = textField_1.getText();
		String senha = new String(passwordField.getPassword());
		String senhaCriptografada = CriptografiaUtils.criptografarMD5(senha);

		UsuarioDao dao = new UsuarioDao();

		Usuario u = dao.encontrarUsuarioPorEmailESenha(email, senhaCriptografada);

		if (u == null) {

			JOptionPane.showMessageDialog(null, "Não foi encontrado usuários");
		} else {

			Principal a = new Principal();
			a.setLocationRelativeTo(null);
			a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			a.setVisible(true);

		}
	}

}
