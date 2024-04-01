package br.com.gerador.transferencias;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.gerador.dto.TransferenciaPixDto;
import br.com.gerador.dto.TransferenciaTedDto;
import br.com.importador.ClienteDados;
import br.com.importador.ImportDados;

public class GeradorTransferencia {

	Random random = new Random();
	
	public List<TransferenciaPixDto> pix = new ArrayList<>();
	public List<TransferenciaTedDto> ted = new ArrayList<>();

	public void gerarPix() {
		ImportDados importar = new ImportDados();
		List<ClienteDados> clientes = importar.listarDados();
		for (int i = 0; i != 10; i++) {
			int conta1 = random.nextInt(clientes.size());
			int conta2 = random.nextInt(clientes.size());
			BigDecimal numeroAleatorio = BigDecimal.valueOf(random.nextDouble() * 200).setScale(2, RoundingMode.HALF_UP);
			if (conta1 != conta2) {
				ClienteDados cliente = clientes.get(conta1);
				int idCliente = cliente.id();
				String cpfDestino = clientes.get(conta2).cpf();

				TransferenciaPixDto transferenciaPix = new TransferenciaPixDto(idCliente, cpfDestino, numeroAleatorio.doubleValue());
				pix.add(transferenciaPix);

			}else {
				i--;
			}
		}
	}

	public List<TransferenciaPixDto> listarTransferenciasPix() {

		return pix;
	}

	public void gerarTed() {
		ImportDados importar = new ImportDados();
		List<ClienteDados> clientes = importar.listarDados();
		for (int i = 0; i != 10; i++) {
			int conta1 = random.nextInt(clientes.size());
			int conta2 = random.nextInt(clientes.size());
			 BigDecimal numeroAleatorio = BigDecimal.valueOf(random.nextDouble() * 200).setScale(2, RoundingMode.HALF_UP);
			if (conta1 != conta2) {
				ClienteDados cliente = clientes.get(conta1);
				int idCliente = cliente.id();
				int numeroConta = clientes.get(conta2).numeroConta();
				int agencia = clientes.get(conta2).agencia();
				
				//agencia, numeroconta, valor, id
				TransferenciaTedDto transferenciaTed = new TransferenciaTedDto(agencia, numeroConta, numeroAleatorio.doubleValue(), idCliente);
				ted.add(transferenciaTed);

			}else {
				i--;
			}
		}
	}

	public List<TransferenciaTedDto> listarTransferenciasTed() {

		return ted;
	}

	

}
