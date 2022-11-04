package com.model;

import implementacoes.CalculaVotoImp;
import implementacoes.CandidatoImpl;
import implementacoes.VotacaoImpl;
import interfaces.CalculaVoto;
import interfaces.VotacaoInterface;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;



public class Servidor {

    static List<CandidatoImpl> candidatos;
    static VotacaoInterface votacaoImpl;
    static int porta = 1099;
    VotacaoInterface votacao = (VotacaoInterface) votacaoImpl;
    public static void main(String[] args) {
        try {
                
            String objName = "rmi://localhost:" + porta + "/server";

            System.out.println("Iniciando Urna Eletrônica...");
            LocateRegistry.createRegistry(porta);

            // Lista de Candidatos
            candidatos = new ArrayList<>();
            candidatos.add(new CandidatoImpl(555, "Mateus"));
            candidatos.add(new CandidatoImpl(222, "Silvio"));
            candidatos.add(new CandidatoImpl(333, "Rodrigo"));
            candidatos.add(new CandidatoImpl(444, "Santos"));
            candidatos.add(new CandidatoImpl(555, "Flash"));
            candidatos.add(new CandidatoImpl(116, "Saitama"));
            candidatos.add(new CandidatoImpl(777, "Goku"));

            votacaoImpl = new VotacaoImpl(candidatos);

            Naming.rebind(objName, votacaoImpl);
            //votacao.apuracao();
            System.out.println("Aguardando Eleitor na urna: " + porta + "!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void apuracao() throws RemoteException, NotBoundException, MalformedURLException{
        System.out.println("Chamando servidor dois para realizar os calculos");
        
        String objName = "rmi://localhost:" + 1100 + "/server";
	CalculaVoto calculoVotacao = (CalculaVoto) Naming.lookup(objName);
        votacao.apuracao(calculoVotacao);
        //calculoVotacao.calculaVotos(2, 3);
        //calculoVotacao.apuracao();
    }
    
    
}
