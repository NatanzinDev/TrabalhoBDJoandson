package dominio;

public class Estoque {
	@Override
	public String toString() {
		return "Estoque " + nometipo ;
	}
	private int id;
	private String nometipo;
	private String lugar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNometipo() {
		return nometipo;
	}
	public void setNometipo(String nometipo) {
		this.nometipo = nometipo;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
}
