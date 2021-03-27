# bigExampleViniciusSouza

##BAD SMELLS

_Cliente_
1) Criação do vetor compras poderia ser feita no construtor, junto com a especificação do nome;
2) O tipo armazenado pelo vetor não estava especificado;
3) O nome do método historico() não descreve exatamente o que o método faz. Seria melhor mudar para historicoDeCompras();
4) O método historico() pode ser quebrado em dois métodos, um getTitulo() para gerar o título do histórico e um getCorpo() para gerar o corpo do histórico;
4) A variável "cada" não descreve exatamente o que a variável é. Seria melhor mudar para "compra";
5) No algoritmo para calcular o total parcial, a variável do tipo Compra usava um método do tipo Anuncio várias vezes. Seria melhor instanciar o tipo Anuncio para que ele mesmo chame o método;
6) No algoritmo para calcular o total parcial, havia um if desnecessário no case IMAGEM que pode pode causar um comportamento indesejável.
7) Há como extrair um método do algoritmo para incrementar pontosFrequentes, chamado calcularPontosFrequentes()

_Anuncio_
1) As constantes IMAGEM, TEXTO e VÍDEO podem ser transformadas em um Enum. Deletei esses campos e criei um Enum chamado CodigosDePreco;
2) O campo codigoPreco deixa de ser do tipo int e passa a ser do tipo CodigosDePreco;
