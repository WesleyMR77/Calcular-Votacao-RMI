package com.model;

import static com.model.Cliente.porta;
import implementacoes.CalculaVotoImp;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import implementacoes.CandidatoImpl;
import implementacoes.IsEvenImpl;
import implementacoes.VotacaoImpl;
import interfaces.VotacaoInterface;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorDois {

    static List<CandidatoImpl> candidatos;
    static VotacaoInterface votacaoImpl;
    static int porta = 1100;
    VotacaoInterface votacao = (VotacaoInterface) votacaoImpl;
    public static void main(String[] args) {
        try {
                
            String objName = "rmi://localhost:" + porta + "/server";

            System.out.println("Iniciando Urna Eletrônica...");
            LocateRegistry.createRegistry(porta);
            System.out.println("Servidor 2 iniciando");
            
            //votacaoImpl = new VotacaoImpl();
      
            Naming.rebind(objName, new CalculaVotoImp());
            System.out.println("Aguardando conexão na porta: " + porta + "!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
