package bigExampleLab;
public class Anuncio {
	private String descricao;
	private CodigosDePreco codigoPreco;

	public Anuncio(String descricao, int codigoPreco) {
		setDescricao(descricao);
		setCodigoPreco(codigoPreco);
	}
	public String getDescricao (){
		return descricao;
	}
	private void setDescricao(String descricao) {
		this.descricao = descricao;
		
	}
	public CodigosDePreco getCodigoPreco() {
		return codigoPreco;
	}
	public void setCodigoPreco(int arg) {
		switch(arg) {
		case 0:
			codigoPreco = CodigosDePreco.IMAGEM;
			break;
		case 1:
			codigoPreco = CodigosDePreco.VIDEO;
			break;
		case 2:
			codigoPreco = CodigosDePreco.TEXTO;
			break;
		default:
			break;
		}
	}
}