import java.util.Scanner;

public class Sort<E>
{
    E[] array;
    int size;

    public Sort()
    {
        le_array();
    }

    public void insert(E[] array)
    {

    }

    public void quick(E[] array)
    {

    }

    public E[] le_array()
    {
        Scanner scanSize = new Scanner(System.in);
        System.out.print("Escreva o tamanho do seu array: ");
        size = scanSize.nextInt();
        array = (E[]) new Object[size];
        E elemento;

        for(int i = 0; i < size; i++)
        {
            Scanner scanElemento = new Scanner(System.in);
            System.out.print(i + 1 + " elemento: ");
            elemento = (E) scanElemento.nextLine();
            array[i] = elemento;
        }
        return array;
    }

    public void printArray(Object[] array)
    {
        System.out.print("Array -> ");

        for(int i = 0; i < size; i++)
        {
            if(i == size - 1)
            {
                System.out.println(array[i]);
            }
            else
            {
                System.out.print(array[i] + "; ");
            }
        }
    }

    public static void main(String[] args)
    {
        Sort<Integer> sort = new Sort<Integer>();
        sort.printArray(sort.array);
    }
}
