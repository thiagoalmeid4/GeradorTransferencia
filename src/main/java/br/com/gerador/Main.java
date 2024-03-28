package br.com.gerador;

import br.com.gerador.transferencias.GeradorTransferencia;

public class Main {
    public static void main(String[] args) {
        
    	GeradorTransferencia gerar = new GeradorTransferencia();
    	
    	gerar.executar();
    	System.out.println(gerar.listarTransferenciasPix());
    }
}