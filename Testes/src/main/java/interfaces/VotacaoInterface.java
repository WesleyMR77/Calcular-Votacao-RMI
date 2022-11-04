package interfaces;

import implementacoes.CalculaVotoImp;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import implementacoes.CandidatoImpl;

public interface VotacaoInterface extends Remote {
	public List<CandidatoImpl> listarCandidatos() throws RemoteException;
	public int salvarVoto(int posicao) throws RemoteException;
	public int buscarCandidato(String numero) throws RemoteException;
	public void apuracao(CalculaVoto calculoVotacao) throws RemoteException;
        public void apuracaoS1() throws RemoteException;
        //public void calculaVotos() throws RemoteException;
}
