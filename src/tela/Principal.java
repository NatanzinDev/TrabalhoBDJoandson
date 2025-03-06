package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 532);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarProduto ca = null;
				try {
					ca = new CadastrarProduto();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();

				}
				ca.setLocationRelativeTo(null);
				ca.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ca.setVisible(true);
			}
		});
		btnNewButton.setBounds(203, 405, 126, 51);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Principal.class.getResource("/tela/Imagem/LogoMercado1.png")));
		lblNewLabel_1.setBounds(48, 30, 461, 346);
		contentPane.add(lblNewLabel_1);
	}
}
