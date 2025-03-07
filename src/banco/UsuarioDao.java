package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dominio.Usuario;

public class UsuarioDao {
	public void cadastrarUsuario(String nome, String senha, String email) throws ClassNotFoundException, SQLException {
		Usuario u = new Usuario();
		
		u.setNome(nome);
		u.setEmail(email);
		u.setSenha(senha);

		Connection conexao = FabricaConexao.criarConexao();

		

		String sql = " INSERT INTO usuario (nome,email,senha) VALUES (?,?,?) ";

		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, u.getNome());
		comando.setString(2, u.getEmail());
		comando.setString(3, u.getSenha());
		comando.execute();

		

		comando.close();
		conexao.close();

		JOptionPane.showMessageDialog(null, "Usuário " + nome + " Cadastro com Sucesso");
	}

	public Usuario encontrarUsuarioPorEmailESenha(String email, String senhaCriptografada) throws ClassNotFoundException, SQLException {
		Connection conexao = FabricaConexao.criarConexao();

		String sql = " SELECT * FROM usuario u WHERE u.email LIKE ? AND u.senha = ? ";

		PreparedStatement comando = conexao.prepareStatement(sql);

		comando.setString(1, email);
		comando.setString(2, senhaCriptografada);

		ResultSet resultado = comando.executeQuery();

		if (resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getInt("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setEmail(resultado.getString("email"));
			usuario.setSenha(resultado.getString("senha"));

			return usuario;
		}

		comando.close();
		conexao.close();

		return null;
	}
}
