package model;

public class Fita {

	private Integer id;
	private String nome;
	private Integer anoLancamento;
	private String sinopse;
	private String duracao;
	
	public Fita() {
		super();
	}
	
	public Fita(Integer id) {
		super();
		this.id = id;
	}

	public Fita(Integer id, String nome, Integer anoLancamento, String sinopse, String duracao) {
		super();
		this.id = id;
		this.nome = nome;
		this.anoLancamento = anoLancamento;
		this.sinopse = sinopse;
		this.duracao = duracao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	
}