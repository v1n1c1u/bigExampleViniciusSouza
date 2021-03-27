/*
 * BAD SMELLS

Cliente
1) Criação do vetor compras poderia ser feita no construtor, junto com a especificação do nome;
2) O tipo armazenado pelo vetor não estava especificado;
3) O nome do método historico() não descreve exatamente o que o método faz. Seria melhor mudar para historicoDeCompras();
4) O método historico() pode ser quebrado em dois métodos, um getTitulo() para gerar o título do histórico e um getCorpo() para gerar o corpo do histórico;
4) A variável "cada" não descreve exatamente o que a variável é. Seria melhor mudar para "compra";
5) No algoritmo para calcular o total parcial, a variável do tipo Compra usava um método do tipo Anuncio várias vezes. Seria melhor instanciar o tipo Anuncio para que ele mesmo chame o método;
6) No algoritmo para calcular o total parcial, havia um if desnecessário no case IMAGEM que pode pode causar um comportamento indesejável.
7) Há como extrair um método do algoritmo para incrementar pontosFrequentes, chamado calcularPontosFrequentes()

Anuncio
1) As constantes IMAGEM, TEXTO e VÍDEO podem ser transformadas em um Enum. Deletei esses campos e criei um Enum chamado CodigosDePreco;
2) O campo codigoPreco deixa de ser do tipo int e passa a ser do tipo CodigosDePreco;

 */
package bigExampleLab;

import java.util.Enumeration;
import java.util.Vector;


class Cliente {
	private String nome;
	private Vector<Compra> compras;

	public Cliente(String nome) {
		this.nome = nome;
		compras  = new Vector<Compra>();
	}

	public void addCompra(Compra arg) {
		compras.addElement(arg);
	}

	public String getNome() {
		return nome;
	}
	
	public String historicoDeCompras() {
		String resultado = "";
		resultado += getTitulo();
		resultado += getCorpo();
		return resultado;
	}

	private String getTitulo() {
		return "Historico de compras de anuncios por " + getNome() + "\n";
	}
	
	private String getCorpo() {
		String corpo = "";
		double total = 0;
		int pontosFrequentes = 0;
		Enumeration<Compra> comprasAnuncio = compras.elements();
		
		while (comprasAnuncio.hasMoreElements()) {
			Compra compra = (Compra) comprasAnuncio.nextElement();
			Anuncio anuncio = compra.getAnuncio();
			
			double totalParcial = calcularTotalParcial(anuncio, compra);
			pontosFrequentes = calcularPontosFrequentes(pontosFrequentes, compra, anuncio);
			
			corpo += "\t" + anuncio.getDescricao() + "\t" + String.valueOf(totalParcial) + "\n";
			total += totalParcial;
		}
		corpo += "Total devido Ã© " + String.valueOf(total) + "\n";
		corpo += "Voce ganhou " + String.valueOf(pontosFrequentes) + " pontos";
		return corpo;
	}

	private int calcularPontosFrequentes(int pontosFrequentes, Compra compra, Anuncio anuncio) {
		pontosFrequentes++;
		
		if ((anuncio.getCodigoPreco() == CodigosDePreco.VIDEO) && compra.getDiasAnuncio() > 1)
			pontosFrequentes++;
		return pontosFrequentes;
	}
	private double calcularTotalParcial(Anuncio anuncio, Compra compra) {
		double totalParcial = 0;
		switch (anuncio.getCodigoPreco()) {
		case IMAGEM:
			totalParcial += 2;
			break;
		case VIDEO:
			totalParcial += compra.getDiasAnuncio() * 3;
			break;
		case TEXTO:
			totalParcial += 1.5;
			if (compra.getDiasAnuncio() > 3)
				totalParcial += (compra.getDiasAnuncio() - 3) * 1.5;
			break;
		}
		return totalParcial;
	}

}