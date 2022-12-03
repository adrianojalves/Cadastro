package model;

public class FitaGenero {

	private Fita codFita;
	private Genero codGenero;
	
	public FitaGenero() {
		super();
	}
	public FitaGenero(Fita codFita, Genero codGenero) {
		super();
		this.codFita = codFita;
		this.codGenero = codGenero;
	}
	public Fita getCodFita() {
		return codFita;
	}
	public void setCodFita(Fita codFita) {
		this.codFita = codFita;
	}
	public Genero getCodGenero() {
		return codGenero;
	}
	public void setCodGenero(Genero codGenero) {
		this.codGenero = codGenero;
	}
	@Override
	public String toString() {
		return "FitaGenero [codFita=" + codFita + ", codGenero=" + codGenero + "]";
	}
	
}