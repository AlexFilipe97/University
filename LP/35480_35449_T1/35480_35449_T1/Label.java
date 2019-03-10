public class Label extends Instrucoes
{
    public int size;
    
    public Label(String id, int tamanho)
    {
        label = id;
        size = tamanho;
    }

    @Override
    public String toString()
    {
    	return label + " " + size;
    }
}
