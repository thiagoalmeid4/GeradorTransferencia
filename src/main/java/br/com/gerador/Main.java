package br.com.gerador;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.gerador.dto.TransferenciaPixDto;
import br.com.gerador.dto.TransferenciaTedDto;
import br.com.gerador.requisicao.RequisicaoPix;
import br.com.gerador.requisicao.RequisicaoTed;
import br.com.gerador.transferencias.GeradorPix;
import br.com.gerador.transferencias.GeradorTed;
import br.com.gerador.transferencias.GeradorTransferencia;

public class Main {
	public static void main(String[] args) throws IOException {

		var gera = new GeradorTransferencia();
		var geraPix = new GeradorPix(gera);
		var geraTed = new GeradorTed(gera);
		var reqPix = new RequisicaoPix();
		var reqTed = new RequisicaoTed();

		boolean segue = true;
		int entrada;

		while (segue) {
			String painel = "[1] gerar transferência pix\n"
					+ "[2] gerar transferência ted\n"
					+ "[3] visualizar transferências pix\n"
					+ "[4] visualizar transferências ted\n"
					+ "[5] enviar transferências\n";

			entrada = Integer.parseInt(JOptionPane.showInputDialog(painel + "\ndigite sua opção:"));

			switch (entrada) {
				case 1:

					geraPix.escreverPix();

					break;
				case 2:
					geraTed.writeTed();
					break;
				case 3:
					int i = 0;
					StringBuilder sb = new StringBuilder("");
					for (TransferenciaPixDto t : geraPix.lerPix()) {
						sb.append(i).append("-");
						sb.append(t).append("\n");
						i++;
					}
					JOptionPane.showMessageDialog(null, sb.toString());
					break;
				case 4:
					i = 0;
					sb = new StringBuilder("");
					for (TransferenciaTedDto t : geraTed.readTed()) {
						sb.append(i).append("-");
						sb.append(t).append("\n");
						i++;
					}
					JOptionPane.showMessageDialog(null, sb.toString());
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

		String painel = "[1] enviar todos pix\n"
				+ "[2] enviar todos ted\n"
				+ "[3] enviar pix específico\n"
				+ "[4] enviar ted específico\n";
		entrada = Integer.parseInt(JOptionPane.showInputDialog(painel + "\ndigite sua opção:"));
		switch (entrada) {
			case 1:
				for (TransferenciaPixDto t : gp.lerPix()) {
					System.out.println("Realizando a transferência...");
					JOptionPane.showMessageDialog(null, rp.executar(t));
				}
				break;
			case 2:
				for (TransferenciaTedDto t : gt.readTed()) {
					System.out.println("Realizando a transferência...");
					JOptionPane.showMessageDialog(null, rt.executar(t));
				}
				break;
			case 3:
				entrada = Integer.parseInt(JOptionPane.showInputDialog( "\ndigite o indice da transferência:"));
				System.out.println("Realizando a transferência...");
				String s = rp.executar(gp.lerPix().get(entrada));
				JOptionPane.showMessageDialog(null, s);
				break;
			case 4:
				entrada = Integer.parseInt(JOptionPane.showInputDialog("\ndigite o indice da transferência:"));
				System.out.println("Realizando a transferência...");
				String s2 = rt.executar(gt.readTed().get(entrada));
				JOptionPane.showMessageDialog(null, s2);
				break;
		}
	}
}