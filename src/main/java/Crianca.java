import jdk.nashorn.internal.runtime.doubleconv.DoubleConversion;

public class Crianca {
    private String name;
    private int idade;

    public Crianca(String name, int idade){
        this.name = name;
        this.idade = idade;
        System.out.println("Criou criança: " + name + " / " + idade + " anos");
    }

    public String getName() {
        return name;
    }

    public int getIdade() {
        return idade;
    }

    public String toString(){
        return name + " / " + idade + " anos";
    }
 
}