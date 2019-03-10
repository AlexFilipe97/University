public class AcessoArgs extends Instrucoes
{
    public String func1;
    public String func2;
    
    public AcessoArgs(String s, String v1, String v2)
		{
            label = s;
            func1 = v1;
            func2 = v2;
        }

    @Override
    public String toString() 
    {
        return "AcessoArgs{" + label + " " + func1 + " " + func2 + '}';
    }
}
