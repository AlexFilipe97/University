public class ManipulacaoInts extends Instrucoes
{
    public String func;
    public ManipulacaoInts(String s, String v)
    {
        label = s;
        func = v;
    }

    @Override
    public String toString() 
    {
        return "ManipulacaoInts{" + label + " " + func + '}';
    }
    
}
