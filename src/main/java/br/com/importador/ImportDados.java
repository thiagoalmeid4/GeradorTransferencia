package br.com.importador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;



public class ImportDados {
	
	public List<ClienteDados> listarDados(){
		List<ClienteDados> listaClientes = new ArrayList<>();
		FileReader fileR;
		BufferedReader bufferedR;
		try {
		JFileChooser jFile = new JFileChooser();
		jFile.showOpenDialog(null);
		fileR = new FileReader(jFile.getSelectedFile().getAbsoluteFile());
		bufferedR = new BufferedReader(fileR);
		while(bufferedR.ready()){
			String linha = bufferedR.readLine();
			listaClientes.add(new ClienteDados(linha.substring(0, 11), Integer.parseInt(linha.substring(11, 19)), Integer.parseInt(linha.substring(19, 23)), Integer.parseInt(linha.substring(23))));
		}

		
		}catch(Exception e) {
			
		}
		
		return listaClientes;
	}

}
