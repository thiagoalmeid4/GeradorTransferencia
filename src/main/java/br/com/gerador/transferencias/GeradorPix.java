package br.com.gerador.transferencias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import br.com.gerador.dto.TransferenciaPixDto;

public class GeradorPix {

	private GeradorTransferencia gerador;
	
	public GeradorPix(GeradorTransferencia gerador) {
		this.gerador = gerador;
	}

	public void escreverPix() throws IOException {
		gerador.gerarPix();
		List<TransferenciaPixDto> listaPix = gerador.listarTransferenciasPix();

		FileWriter writer = new FileWriter("TransferenciasPix.txt");
		BufferedWriter bWriter = new BufferedWriter(writer);

		for (TransferenciaPixDto tPix : listaPix) {
			String linha = tPix.cpfDestino() + tPix.valor() + '*' + tPix.idContaOrigem();
			bWriter.write(linha);
			bWriter.newLine();
		}
		bWriter.flush();
		bWriter.close();
	}

	public List<TransferenciaPixDto> lerPix (){
		List<TransferenciaPixDto> lista = new ArrayList<TransferenciaPixDto>();
		
		FileReader fileR;
		BufferedReader bufferedR;
		try {
		fileR = new FileReader("TransferenciasPix.txt");
		bufferedR = new BufferedReader(fileR);
		while(bufferedR.ready()){
			String linha = bufferedR.readLine();
			int n = linha.indexOf('*');
			lista.add(new TransferenciaPixDto(linha.substring(0, 11), Double.parseDouble(linha.substring(11, n)), Long.parseLong(linha.substring(n+1))));
		}
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	return lista;

}

}
