package edaI_T1;
import java.util.StringTokenizer;

public class InfixToPost
{
    public InfixToPost()
    {
        StringTokenizer expressao = new StringTokenizer("( 6 + 2 ) * 3");
        ArrayStack<Object> pilha = new ArrayStack<>(expressao.countTokens());
        boolean verbose = false;
        System.out.println(conversao(expressao, pilha, verbose));
    }

    public InfixToPost(String exp, boolean verbose)
    {
        StringTokenizer expressao = new StringTokenizer(exp);
        ArrayStack<Object> pilha = new ArrayStack<>(expressao.countTokens());
        System.out.println(conversao(expressao, pilha, verbose));
    }

    public static int prioridade(String simbolo)
    {
        if(simbolo.equals("(") || simbolo.equals(")"))
        {
            return 3;
        }
        else if(simbolo.equals("*") || simbolo.equals("/"))
        {
            return 2;
        }
        else if(simbolo.equals("+") || simbolo.equals("-"))
        {
            return 1;
        }
        return 0;
    }

    public static String conversao(StringTokenizer expressao, ArrayStack pilha, boolean verbose)
    {
        String output = new String("");

        while (expressao.hasMoreTokens())
        {
            String token = expressao.nextToken();

            if(prioridade(token) == 0)
            {
                output += token + " ";
            }
            else
            {
                pilha.toString();

                if(token.equals(")"))
                {
                    while(!pilha.empty() && !pilha.top().equals("("))
                    {
                        if(verbose)
                        {
                            System.out.println("pilha.pop()");
                        }
                        output += pilha.pop() + " ";
                    }
                    if(verbose)
                    {
                        System.out.println("pilha.pop()");
                    }
                    pilha.pop();
                }
                else
                {
                    if(pilha.empty() || token.equals("("))
                    {
                        if(verbose)
                        {
                            System.out.println("pilha.push(" + token + ")");
                        }
                        pilha.push(token);
                    }
                    else if(prioridade(token) > prioridade((String) pilha.top()))
                    {
                        if(verbose)
                        {
                            System.out.println("pilha.push(" + token + ")");
                        }
                        pilha.push(token);
                    }
                    else
                    {
                        while(!pilha.empty() && !pilha.top().equals("(") &&  prioridade((String) pilha.top()) >= prioridade(token))
                        {
                            if(verbose)
                            {
                                System.out.println("pilha.pop()");
                            }
                            output += pilha.pop() + " ";
                        }
                        if(verbose)
                        {
                            System.out.println("pilha.push(" + token + ")");
                        }
                        pilha.push(token);
                    }
                }
            }
        }
        while(!pilha.empty() && !pilha.top().equals("("))
        {
            pilha.toString();

            if(verbose)
            {
                System.out.println("pilha.pop()");
            }
            output += pilha.pop() + " ";
        }
        return "O resultado final é: " + output;
    }
}