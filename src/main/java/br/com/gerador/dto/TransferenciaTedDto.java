package br.com.gerador.dto;

public record TransferenciaTedDto(
		int agencia,
		int numeroConta,
		double valor,
		long id
		) {

}
