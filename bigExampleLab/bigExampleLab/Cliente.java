/*
 * BAD SMELLS

Cliente
1) Cria��o do vetor compras poderia ser feita no construtor, junto com a especifica��o do nome;
2) O tipo armazenado pelo vetor n�o estava especificado;
3) O nome do m�todo historico() n�o descreve exatamente o que o m�todo faz. Seria melhor mudar para historicoDeCompras();
4) O m�todo historico() pode ser quebrado em dois m�todos, um getTitulo() para gerar o t�tulo do hist�rico e um getCorpo() para gerar o corpo do hist�rico;
4) A vari�vel "cada" n�o descreve exatamente o que a vari�vel �. Seria melhor mudar para "compra";
5) No algoritmo para calcular o total parcial, a vari�vel do tipo Compra usava um m�todo do tipo Anuncio v�rias vezes. Seria melhor instanciar o tipo Anuncio para que ele mesmo chame o m�todo;
6) No algoritmo para calcular o total parcial, havia um if desnecess�rio no case IMAGEM que pode pode causar um comportamento indesej�vel.
7) H� como extrair um m�todo do algoritmo para incrementar pontosFrequentes, chamado calcularPontosFrequentes()

Anuncio
1) As constantes IMAGEM, TEXTO e V�DEO podem ser transformadas em um Enum. Deletei esses campos e criei um Enum chamado CodigosDePreco;
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
		corpo += "Total devido é " + String.valueOf(total) + "\n";
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