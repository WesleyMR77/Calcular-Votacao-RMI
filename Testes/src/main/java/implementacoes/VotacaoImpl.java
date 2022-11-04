package implementacoes;

import com.model.Servidor;
import interfaces.CalculaVoto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import interfaces.VotacaoInterface;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VotacaoImpl extends UnicastRemoteObject implements VotacaoInterface {

    private static final long serialVersionUID = 1L;
    private List<CandidatoImpl> candidatos;
    private List<VotoImpl> votos = new ArrayList<>();
    private Map<Integer, Integer> quantidade = new HashMap<>();

    public VotacaoImpl() throws RemoteException {
    }

    public VotacaoImpl(List<CandidatoImpl> candidatos) throws RemoteException {
        super();
        this.candidatos = candidatos;
    }

    @Override
    public List<CandidatoImpl> listarCandidatos() throws RemoteException {
        return this.candidatos;
    }

    @Override
    public int salvarVoto(int posicao) throws RemoteException {
        this.votos.add(new VotoImpl("123", this.candidatos.get(posicao)));
        return posicao;
    }

    @Override
    public void apuracao(CalculaVoto calculoVotacao) throws RemoteException {
        int total = 0;
        this.votos.forEach(voto -> {
            int count = (int) votos.stream().filter(p -> p.getCandidato().equals(voto.getCandidato())).count();

            quantidade.put(voto.getCandidato().getNumero(), count);

            //System.out.println(voto.getCandidato().getNome());
        });
        final String format = "O candidato %d possui %d voto(s), tendo %.2f%s da apuração dos votos";
        final Set<Integer> chaves = quantidade.keySet();
        System.out.println("Apuração dos votos");

        for (final Integer chave : chaves) {
            total += quantidade.get(chave);
        }
        
        System.out.println("-----------------------------------");
        for (final Integer chave : chaves) {
             float porcentagem = calculoVotacao.calculaVotos(quantidade.get(chave), total);
            //calcularVotos(total, quantidade.get(chave));
            System.out.println(String.format(format, chave, quantidade.get(chave), porcentagem, '%'));
        }
        System.out.println("-----------------------------------");
    }

    @Override
    public int buscarCandidato(String numero) throws RemoteException {
        for (int i = 0; i < this.candidatos.size(); i++) {
            if (this.candidatos.get(i).getNumero() == Integer.parseInt(numero)) {
                return this.salvarVoto(i);
            }
        }

        return -1;
    }
    
    public void apuracaoS1() throws RemoteException {
            Servidor servidor = new Servidor();
        try {
            servidor.apuracao();
        } catch (NotBoundException ex) {
            Logger.getLogger(VotacaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(VotacaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
