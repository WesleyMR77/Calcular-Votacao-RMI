/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementacoes;

import interfaces.CalculaVoto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author wesle
 */
public class CalculaVotoImp  extends UnicastRemoteObject implements CalculaVoto{
    
    public CalculaVotoImp() throws RemoteException
    {
        System.out.println("Novo Servidor instanciado...");
    }
    @Override
    public float calculaVotos(int quantidade, int total) throws RemoteException {
        
        return ((float)((quantidade * 100.00) / total)) ;
    }

    

    
}
