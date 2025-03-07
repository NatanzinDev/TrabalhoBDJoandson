package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class CadastrarEstoque extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarEstoque frame = new CadastrarEstoque();
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
	public CadastrarEstoque() {
		setTitle("Cadastrar Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastrar Tipo de Estoque", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(71, 48, 507, 279);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(138, 93, 257, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(138, 145, 257, 19);
		panel.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Nome do estoque");
		lblNewLabel.setBounds(141, 81, 107, 13);
		panel.add(lblNewLabel);
		
		JLabel lblLocalDoEstoque = new JLabel("Local do estoque");
		lblLocalDoEstoque.setBounds(138, 134, 107, 13);
		panel.add(lblLocalDoEstoque);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBounds(140, 204, 140, 21);
		panel.add(btnNewButton);
	}
}
