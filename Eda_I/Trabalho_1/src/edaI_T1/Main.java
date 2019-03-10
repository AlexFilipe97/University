package edaI_T1;
import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {

        String exp;
        Scanner exp_scan = new Scanner(System.in);
        System.out.println("Escreva a expressão: ");
        exp = exp_scan.nextLine();

        InfixToPost infix = new InfixToPost(exp, true);

        EvalPostfix eval = new EvalPostfix(exp, true);
    }
}