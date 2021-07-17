import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

import javax.swing.plaf.synth.SynthToolTipUI;

import org.graalvm.compiler.nodes.java.NewArrayNode;

import jdk.tools.jlink.internal.SymLinkResourcePoolEntry;

public class PulaPula {
    private int limiteMax;
    private Double caixa = 0.0 ;
    List <Crianca> criancasPulando = new ArrayList<>();
    List <Crianca> filaDeEspera = new ArrayList<>();
    List <Conta> contas = new ArrayList<>();

    public PulaPula(int limiteMax) {
        this.limiteMax = limiteMax;
        System.out.println("-----------------------\nPP Criado!"); 
        mostraPP();
    }

    public List<Crianca> getFilaDeEspera() {
        return filaDeEspera;
    }

    public List<Crianca> getCriancasPulando() {
        return criancasPulando;
    }

    public List<Conta> getContas(){
        return contas;
    }

    public int getLimiteMax() {
        return limiteMax;
    }

    public Double getCaixa() {
        return caixa;
    }

    public Double getConta(String name) {
        for (Conta conta : contas) {
            if(conta.getNome().equals(name)) {
                int i = contas.indexOf(conta);
                System.out.println("Conta encontrada!! " + conta);
                return contas.get(i).getValor();         
            }
        }
        System.out.println("Não existe conta com esse nome!");
        //mostraPP();
        return null;
    }

    public void setCaixa(Double valor) {
        caixa += valor;
        System.out.println("Caixa recebeu: " + valor +
                            " / Caixa atualizado: " + caixa);
    }

    public boolean entrarNaFila(Crianca crianca) {   
        System.out.println(crianca + " pediu para entrar na fila!");
        for (Crianca element : filaDeEspera) {
            if(crianca.getName().equals(element.getName())){
                System.out.println("Já existe criança com mesmo nome na fila!");
                //mostraPP();
                return false;
            }
        }
        filaDeEspera.add(crianca);
        System.out.println(crianca + " entrou na fila!");
        //mostraPP();
        return true;
    }

    public boolean entrar() {
        if(filaDeEspera.isEmpty()){
            System.out.println("Não há crianças na fila de espera!");
            //mostraPP();
            return false;
        }else if(criancasPulando.size() >= limiteMax){
            System.out.println("O pula pula está lotado!");
            //mostraPP();
            return false;
        }else{
            System.out.println("Vez do(a) " + filaDeEspera.get(0).getName() + "!");
            //mostraPP();
            for (Conta element : contas) {
                if(filaDeEspera.get(0).getName().equals(element.getNome())){
                    System.out.println(filaDeEspera.get(0).getName() + " já tem conta!");
                    criancasPulando.add(filaDeEspera.get(0));
                    filaDeEspera.remove(0);
                    System.out.println("Saui da fila e entrou no PP!!");
                    element.setValor();
                    //mostraPP();
                    return true;
                }
            }
            
            Conta conta = new Conta(filaDeEspera.get(0).getName());
            contas.add(conta);
            criancasPulando.add(filaDeEspera.get(0));
            filaDeEspera.remove(0);
            System.out.println("Saiu da fila e entrou no PP!!");
            conta.setValor();
            mostraPP();
            return true;
        }
    }

    public boolean sair() {
        if(criancasPulando.isEmpty()){
            System.out.println("Não há crianças pulando para sair!");
            //mostraPP();
            return false;
        }else{
            System.out.println("Está pulando!");
            //mostraPP();
            filaDeEspera.add(criancasPulando.get(0));
            criancasPulando.remove(0);
            System.out.println("Saiu do PP para Fila!");
            //mostraPP();
            return true;
        }
        
    }
    
    public boolean criancaNaFila(String name) {
        for (Crianca crianca : filaDeEspera) {
            if (crianca.getName().equals(name)){
                System.out.println("crianca na fila");
                return true;
            }
        }
        System.out.println("crianca não tá na fila");
        return false;
    }

    public boolean criancaNoPula(String name) {
        for (Crianca crianca : criancasPulando) {
            if (crianca.getName().equals(name)){
                System.out.println("crianca no PP");
                return true;
            } 
        }
        System.out.println("crianca nao ta no PP");
        return false;
    }

    public boolean temConta(String Name) {
        for (Conta conta : contas) {
            if(conta.getNome().equals(Name)){
                System.out.println("Tem conta");
                return true;
            }
        }
        System.out.println("Nao tem conta");
        return false;
    }

    public boolean papaiChegou(String name) {
        if(criancaNaFila(name)){
            if(temConta(name)){
                setCaixa(getConta(name));
                return true;
            }
            return true;
        }
        if(criancaNoPula(name)){
            setCaixa(getConta(name));
            return true;
        }
        return false;
    }

    public Double fechar() {
        System.out.println("O PP vai ser fechado! Situação atual: ");
        mostraPP();
        for (Conta conta : contas) {
            caixa += getConta(conta.getNome());

        }
        contas.clear();
        criancasPulando.clear();
        filaDeEspera.clear();
        System.out.println("O PP foi encerrado!");
        mostraPP();
        return caixa;
    }

    public void mostraPP() {
        System.out.println("Pula Pula\n\tLimite Maximo: " + limiteMax +
                            "\n\tCaixa: " + caixa +
                            "\n\tPulando: " + criancasPulando +
                            "\n\tFila: " + filaDeEspera +
                            "\n\tContas: " + contas);
    }

}