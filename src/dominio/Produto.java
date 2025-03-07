package dominio;

public class Produto {

	public String toString() {
		return "Nome do Produto: " + nome + "  -  " + "Id: " + id;
	}

	private int id;
	private String nome;
	private String preco;
	private String validade;
	private Estoque tipodeestoque;



	public Estoque getTipodeestoque() {
		return tipodeestoque;
	}

	public void setTipodeestoque(Estoque tipodeestoque) {
		this.tipodeestoque = tipodeestoque;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

}
