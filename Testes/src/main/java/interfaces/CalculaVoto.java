/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculaVoto extends Remote{
   
    public float calculaVotos(int a, int b) throws RemoteException;  
}
