public class Conta {
    private String nome;
    private Double valor = 0.0;

    public Conta(String nome){
        this.nome = nome;
        System.out.println("Criou conta: " + nome + " / " + valor);
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor() {
        System.out.println("Foi acrescentado R$ 2.50 na conta!");
        valor += 2.5;
    }

    public String toString(){
        return  nome + " / " + valor;
    }
}

