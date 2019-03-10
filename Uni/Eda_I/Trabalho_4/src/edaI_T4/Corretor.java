package edaI_T4;

import java.io.*;
import java.util.StringTokenizer;

public class Corretor
{
    BufferedReader reader;
    QuadHashTable dicionario;

    public Corretor() throws IOException
    {
        dicionario = new QuadHashTable(11);
        File file = new File("/home/alex/Documents/uni/edaI/Trabalho_4/src/edaI_T4/dicionario.txt");
        reader = new BufferedReader(new FileReader(file));
        String palavra;

        while((palavra = reader.readLine()) != null)
        {
            dicionario.insere(palavra);
        }
    }

    public void verificaFicheiro() throws IOException
    {
        File file = new File("/home/alex/Documents/uni/edaI/Trabalho_4/src/edaI_T4/texto.txt");
        BufferedReader ficheiro = new BufferedReader(new FileReader(file));
        String linha = ficheiro.readLine();
        int counter = 1;

        while(linha != null)
        {
            StringTokenizer texto = new StringTokenizer(linha);

            while(texto.hasMoreTokens())
            {
                String palavra = texto.nextToken();

                if(!verificaPalavra(palavra))
                {
                    System.out.println("Linha " + counter + " \"" + palavra + "\" " + " -> " + sugestaoRemovendo(palavra) + sugestaoAdicionando(palavra));
                }
            }
            linha = ficheiro.readLine();
            counter++;
        }
        System.out.println("O texto foi lido com sucesso!");
    }

    public boolean verificaPalavra(String palavra)
    {
        if(dicionario.procurar(palavra) != null)
        {
            return true;
        }
        return false;
    }

    public String sugestaoRemovendo(String palavra)
    {
        String output = "";

        for(int i = 0; i < palavra.length(); i++)
        {
            String novaPalavra = "";
            StringBuilder temp = new StringBuilder(palavra);
            StringBuilder sugestao = temp.deleteCharAt(i);
            novaPalavra += sugestao;

            if(dicionario.procurar(novaPalavra) != null)
            {
                output += novaPalavra + " | ";
            }
        }
        return output;
    }

    public String sugestaoAdicionando(String palavra)
    {
        String output = "";
        int counter = 0;

        while(counter <= palavra.length())
        {
            for(char i = 'a'; i < 'z'; i++)
            {
                String novaPalavra = "";
                StringBuilder temp = new StringBuilder(palavra);
                StringBuilder sugestao = temp.insert(counter, i);
                novaPalavra += sugestao;

                if(dicionario.procurar(novaPalavra) != null)
                {
                    output += novaPalavra + " | ";
                }
            }
            counter++;
        }
        return output;
    }
}
