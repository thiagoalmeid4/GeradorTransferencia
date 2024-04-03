package br.com.gerador.transferencias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import br.com.gerador.dto.TransferenciaTedDto;

public class GeradorTed {

	private GeradorTransferencia gerador;
	
	public GeradorTed(GeradorTransferencia gerador) {
		this.gerador = gerador;
	}
	
	
	public void writeTed() {
		gerador.gerarTed();
		List<TransferenciaTedDto> transferenciasTed = gerador.listarTransferenciasTed();
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		try {
			fileWriter = new FileWriter("TransferenciasTed.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
			for (TransferenciaTedDto transferencia : transferenciasTed) {	
				bufferedWriter.write(transferencia.agencia() + "" + transferencia.numeroConta() + transferencia.id() + "*"
						+ transferencia.valor());
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
			bufferedWriter.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public List<TransferenciaTedDto> readTed() {
		FileReader fileReader;
		BufferedReader bufferedReader;
		List<TransferenciaTedDto> transferenciasTed = gerador.listarTransferenciasTed();
		try {
			fileReader = new FileReader("TransferenciasTed.txt");
			bufferedReader = new BufferedReader(fileReader);
			bufferedReader.readLine();
			while (bufferedReader.ready()) {
				String linha = bufferedReader.readLine();
				String[] dados = linha.split("*");
					String dadosTransferencia = dados[0];
					BigDecimal valor = new BigDecimal(dados[1]).setScale(2, RoundingMode.HALF_UP);

					int agencia = Integer.parseInt(dadosTransferencia.substring(0, 4));
					int numeroConta = Integer.parseInt(dadosTransferencia.substring(4, 12));
					long idCliente = Long.parseLong(dadosTransferencia.substring(12));

					TransferenciaTedDto transferencia = new TransferenciaTedDto(agencia, numeroConta,
							valor.doubleValue(), idCliente);
					transferenciasTed.add(transferencia);
				
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return transferenciasTed;
	}

}
