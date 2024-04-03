package br.com.gerador;

import java.io.IOException;
import java.util.Scanner;

import br.com.gerador.dto.TransferenciaPixDto;
import br.com.gerador.dto.TransferenciaTedDto;
import br.com.gerador.requisicao.RequisicaoPix;
import br.com.gerador.requisicao.RequisicaoTed;
import br.com.gerador.transferencias.GeradorPix;
import br.com.gerador.transferencias.GeradorTed;
import br.com.gerador.transferencias.GeradorTransferencia;

public class Main {
    public static void main(String[] args) throws IOException {
      
    	Scanner scanner = new Scanner(System.in);
    	var gera = new GeradorTransferencia();
    	var geraPix = new GeradorPix(gera);
    	var geraTed = new GeradorTed(gera);
    	var reqPix = new RequisicaoPix();
    	var reqTed = new RequisicaoTed();
    	
    	boolean segue = true;
    	int entrada;
    	
    	while(segue) {
    		System.out.println("[1] gerar transferência pix\n"
    				+ "[2] gerar transferência ted\n"
    				+ "[3] visualizar transferências pix\n"
    				+ "[4] visualizar transferências ted\n"
    				+ "[5] enviar transferências pix\n"
    				+ "[6] enviar transferências ted\n");
    		entrada = scanner.nextInt();
    		
    		switch(entrada) { 
    		case 1:
    			
    			geraPix.escreverPix();
    			
    			break;
    		case 2:
    			geraTed.writeTed();
    			break;
    		case 3:
    			for(TransferenciaPixDto t : geraPix.lerPix()) {
    				System.out.println(t);
    			}
    			break;
    		case 4:
    			for (TransferenciaTedDto t : geraTed.readTed()) {
    				System.out.println(t);
    			}
    			break;
    		case 5:
    			menuEnviar(geraPix, geraTed, reqPix, reqTed);
    			break;
    		
    		default:
    			segue = false;
    			break;
    		}
    	}
    	
    }
    
    public static void menuEnviar(GeradorPix gp, GeradorTed gt, RequisicaoPix rp, RequisicaoTed rt) throws IOException {
    	Scanner scanner = new Scanner(System.in);
    	int entrada;
    	
    	System.out.println("[1] enviar todos pix\n"
				+ "[2] enviar todos ted\n"
				+ "[3] enviar pix específico\n"
				+ "[4] enviar ted específico\n"
				);
    	entrada = scanner.nextInt();
    	switch(entrada) {
    	case 1:
    		for (TransferenciaPixDto t : gp.lerPix()) {
				rp.executar(t);
				}
    		break;
    	case 2:
    		for (TransferenciaTedDto t : gt.readTed()) {
				rt.executar(t);
				}
    		break;
    	case 3:
    		entrada = scanner.nextInt();
    		rp.executar(gp.lerPix().get(entrada));
    		break;
    	case 4:
    		entrada = scanner.nextInt();
    		rt.executar(gt.readTed().get(entrada));
    		break;
    	}
    }
}