package javaapplication1;
import java.io.IOException;
//import java.util.LinkedList;

public class JavaApplication1
{
    /*
    public static void insertSort(String nome, LinkedList<String> list)
    {
        int p = 0;
        while(p < list.size() && list.get(p).compareTo(nome) < 0)
        {
            p++;
        }
        list.add(p, nome);
    }
    */
    
    public static void main(String[] args) throws IOException
    {   
        
        try
        {
            byte[] b = new byte[128];
            int lidos = System.in.read(b);
            String s = new String(b, 0, lidos - 1); // ou -2 no windows
            int valor = Integer.parseInt(s.substring(0, lidos - 1));
            valor++;
            System.out.println("valor: " + valor);
        }
        catch(NumberFormatException notInt)
        {
            System.out.println("Deve inserir um inteiro!");
        }
        /*
        
        for(String nomes:args)
        {
            System.out.println(nomes);
        }
        LinkedList<String> list = new LinkedList<String>();
        for(String nome:args)
        {
            insertSort(nome, list);
        }
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
        */
    }
    
}
