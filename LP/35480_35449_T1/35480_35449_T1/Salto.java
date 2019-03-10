public class Salto extends Instrucoes
{
    public String func;
    
    public Salto(String s, String v)
    {
        label = s;
        func = v;
    }

    @Override
    public String toString() 
    {
        return "Salto{" + label + " " + func + '}';
    }
}