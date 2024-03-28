package br.com.gerador.dto;

public record TransferenciaPixDto (
		long idContaOrigem,
		String cpfDestino,
		double valor
		) {
	
}
