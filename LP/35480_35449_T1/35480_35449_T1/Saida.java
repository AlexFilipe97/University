public class Saida extends Instrucoes
{
    public String func;
    
    public Saida(String s, String v)
    {
        label = s;
        func = v;
    }
    
    public Saida(String s)
    {
        label = s;
    }

    @Override
    public String toString() 
    {
        switch(label)
        {
            case ("escreve_str"):
                return "Saida{" + label + " " + func + '}';
            default:
                return "Saida{" + label + '}';
        }
    }
}
