public class ChamadaFunc extends Instrucoes
{
    public String func1;
    public String func2;
    
    public ChamadaFunc(String s, String v1, String v2)
    {
        label = s;
        func1 = v1;
        func2 = v2;
    }
    
    public ChamadaFunc(String s, String v)
    {
        label = s;
        func1 = v;
    }
    
    public ChamadaFunc(String s)
    {
        label = s;
    }

    @Override
    public String toString() 
    {
    	switch(label)
    	{
    		case "regressa":
    			return "ChamadaFunc{" + label + '}';

    		case "coloca_arg":
    			return "ChamadaFunc{" + label + " "+ func1 + '}';

    		default:
    			return "ChamadaFunc{" + label + " "+ func1 + " " + func2 + '}';
    	}
    }
}
