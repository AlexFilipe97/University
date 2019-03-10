package edaI_T1;
import java.util.StringTokenizer;

public class EvalPostfix
{
    public EvalPostfix()
    {
        StringTokenizer expressao = new StringTokenizer("23 56 - 3 * 1 3 4 / + /");
        ArrayStack<Object> pilha = new ArrayStack<>(expressao.countTokens());
        boolean verbose = false;
        System.out.println(avalicao(expressao, pilha, verbose));
    }

    public EvalPostfix(String exp, boolean verbose)
    {
        StringTokenizer expressao = new StringTokenizer(exp);
        ArrayStack<Object> pilha = new ArrayStack<>(expressao.countTokens());
        System.out.println(avalicao(expressao, pilha, verbose));
    }

    public static boolean isItNum(String simbolo)
    {
        if(simbolo.equals("+") || simbolo.equals("-") || simbolo.equals("*") || simbolo.equals("/"))
        {
            return false;
        }
        return true;
    }

    public static Object avalicao(StringTokenizer expressao, ArrayStack pilha, boolean verbose)
    {
        while(expressao.hasMoreTokens())
        {
            String token = expressao.nextToken();

            pilha.toString();

            if(isItNum(token))
            {
                pilha.push(token);
                if(verbose)
                {
                    System.out.println("pilha.push(" + token + ")");
                }
            }
            else
            {
                if(verbose)
                {
                    System.out.println("int temp1 = pilha.pop()");
                    System.out.println("int temp2 = pilha.pop()");
                }

                double temp1 = Double.parseDouble(String.valueOf(pilha.pop()));
                double temp2 = Double.parseDouble(String.valueOf(pilha.pop()));

                if(token.equals("+"))
                {
                    pilha.push(temp2 + temp1);
                    if(verbose)
                    {
                        System.out.println("pilha.push(" + temp2 + " + " + temp1 + ")");
                    }
                }
                else if(token.equals("-"))
                {
                    pilha.push(temp2 - temp1);
                    if(verbose)
                    {
                        System.out.println("pilha.push(" + temp2 + " - " + temp1 + ")");
                    }
                }
                else if(token.equals("*"))
                {
                    pilha.push(temp2 * temp1);
                    if(verbose)
                    {
                        System.out.println("pilha.push(" + temp2 + " * " + temp1 + ")");
                    }
                }
                else if(token.equals("/"))
                {
                    pilha.push(temp2 / temp1);
                    if(verbose)
                    {
                        System.out.println("pilha.push(" + temp2 + " / " + temp1 + ")");
                    }
                }
            }
        }
        pilha.toString();
        if(verbose)
        {
            System.out.println("pilha.pop()");
        }
        return pilha.pop();
    }
}
