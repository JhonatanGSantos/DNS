/**
 * @version 1.0 11/9/2019
 * @author JhonatanGSantos - IFG Câmpus Inhumas 
 * Todos os Direitos Reservados
 */

package br.edu.ifg.cliente;

import br.edu.ifg.interf.*;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AplicacaoChat {

	private AplicacaoChat() {

	}

	public static void main(String[] args) {
		try {

			Registry registry = LocateRegistry.getRegistry("192.168.1.101", Constant.RMI_PORT);
			final InterfaceDNS remote = (InterfaceDNS) registry.lookup(Constant.RMI_ID);
			InetAddress ip = InetAddress.getLocalHost();
			String hostname = ip.getHostAddress();

			String nick = JOptionPane.showInputDialog("Digite o nick");
			//JOptionPane.showMessageDialog(null, nick);

			if (remote.autentica(nick, hostname)) {
				System.out.println("Usuario adicionado com sucesso!");
			} else {
				System.out.println("Erro ao adicionar!");
			}
			ArrayList<String> lista = new ArrayList<>();

			lista = remote.obterListaUsuariosOnline();
			System.out.println("Nome dos usuarios On line:");
			for (String nomeOnline : lista) {
				System.out.println("" + nomeOnline);
			}

			System.out.println("O IP do Usuario: " + nick + "e: " + remote.obterIP("jhonatan"));

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}

	}

}