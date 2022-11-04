package com.model;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

import implementacoes.CandidatoImpl;
import interfaces.VotacaoInterface;

public class Cliente {
	
	static int porta = 1099;
		
	public static void main(String[] args) throws Exception {
		String objName = "rmi://localhost:" + porta + "/server";
		VotacaoInterface votacao = (VotacaoInterface) Naming.lookup(objName);
		
		List<CandidatoImpl> candidatos = votacao.listarCandidatos();
                System.out.println("----------------------------------------------");
		System.out.println("Seja Muito Bem vindo as eleições 2022 :)"
                        + "\nO seu voto será utilizado para decidir o "
                        + "\ngovernante do mundo º.º ");
                System.out.println("----------------------------------------------");
                
            candidatos.forEach(x -> {
                System.out.println(x.getNome() + " " + x.getNumero());
            });
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite o número do voto:");
		String numero_candidato = entrada.nextLine();
		
		int posicao = votacao.buscarCandidato(numero_candidato);
		System.out.println("Seu voto foi: " + candidatos.get(posicao).getNome());
                votacao.apuracaoS1();
                //votacao.apuracao();
	}
}