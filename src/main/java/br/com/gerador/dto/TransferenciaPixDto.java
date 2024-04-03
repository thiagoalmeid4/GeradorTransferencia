package br.com.gerador.dto;

public record TransferenciaPixDto (
		String cpfDestino,
		double valor,
		long idContaOrigem
		
		) {
	
}
