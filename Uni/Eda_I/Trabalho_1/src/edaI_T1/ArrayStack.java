package edaI_T1;

public class ArrayStack<E> implements Stack<E>
{
    E[] stack;
    int top;
    int tamanho;

    public ArrayStack(int tamanho)
    {
        this.tamanho = tamanho;
        if(tamanho > 0)
        {
            stack = (E[]) new Object[tamanho];
            top = 0;
        }
    }

    public void push(E o)
    {
        if(top == tamanho)
        {
            System.out.println("Impossível inserir, stack cheia.");
            return;
        }
        stack[top] = o;
        top++;
    }

    public E top()
    {
        if(top == 0)
        {
            System.out.println("Stack vazia.");
        }
        return stack[top - 1];
    }

    public E pop()
    {
        if(top == 0)
        {
            System.out.println("Impossível fazer pop, stack vazia.");
        }
        else if(top > 0)
        {
            E toPop = stack[top - 1];
            stack[top - 1] = null;
            top--;
            return toPop;
        }
        return null;
    }

    public int size()
    {
        return top;
    }

    public boolean empty()
    {
        return (top == 0);
    }

    public String toString()
    {
        System.out.print("pilha: ");
        for(int i = 0; i < tamanho; i++)
        {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
        return "";
    }
}
