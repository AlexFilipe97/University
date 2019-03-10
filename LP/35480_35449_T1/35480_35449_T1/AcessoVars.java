public class AcessoVars extends Instrucoes
{
    public String func1;
    public String func2;
    public AcessoVars(String s, String v1, String v2)
            {
                label = s;
                func1 = v1;
                func2 = v2;
            }

	@Override
    public String toString() 
    {
        return "AcessoVars{" + label + " " + func1 + " " + func2 + '}';
    }
}
