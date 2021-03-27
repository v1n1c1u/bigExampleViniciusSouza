package bigExampleLab;
class Compra {
	private Anuncio anuncio;
	private int diasAnuncio;
	
	public Compra(Anuncio anuncio, int diasAnuncio) {
		this.anuncio = anuncio;
		this.diasAnuncio = diasAnuncio;
	}
	public int getDiasAnuncio() {
		return diasAnuncio;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
}