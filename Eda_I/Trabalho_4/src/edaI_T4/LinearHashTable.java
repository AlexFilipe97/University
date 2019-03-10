package edaI_T4;

public class LinearHashTable<E>
{
    int hashSize;
    int occupied;
    int maxDifference;
    Elemento[] table;
    Elemento[] tempTable;

    public class Elemento<E>
    {
        E item;
        boolean removed;

        public Elemento(E item)
        {
            this.item = item;
            removed = false;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item)
        {
            this.item = item;
        }
    }

    public LinearHashTable(int hashSize)
    {
        this.hashSize = hashSize;
        alocarTabela(this.hashSize);
        occupied = 0;
        maxDifference = 0;
    }

    public boolean isPrim(int number)
    {
        if(number == 2 || number ==3)
        {
            return true;
        }
        for(int i = 3; i < number; i++)
        {
            if(number % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public int nextPrim(int start)
    {
        if(start % 2 == 0)
        {
            start++;
        }
        while(!isPrim(start))
        {
            start++;
        }
        return start;
    }

    public int ocupados()
    {
        return occupied;
    }

    public float fatorCarga()
    {
        return (float) occupied / hashSize;
    }

    public int procPos(E item)
    {
        int pos = Math.abs(item.hashCode() % hashSize);
        int counter = 0;

        if(procurar(item) != null)
        {
            while(counter < maxDifference)
            {
                if(table[pos] != null && !table[pos].removed && table[pos].getItem().equals(item))
                {
                    return pos;
                }
                pos++;
                if(pos > this.hashSize - 1)
                {
                    pos = 0;
                }
            }
        }
        else
        {
            while(table[pos] != null)
            {
                if(table[pos].removed)
                {
                    return pos;
                }
                pos++;
                if(pos > this.hashSize - 1)
                {
                    pos = 0;
                }
            }
        }
        return pos;
    }

    public void alocarTabela(int dimensao)
    {
        table = new Elemento[dimensao];
        this.hashSize = dimensao;
        maxDifference = 0;
    }

    public void tornarVazia()
    {
        tempTable = new Elemento[this.hashSize];

        for(int i = 0; i < this.hashSize; i++)
        {
            if(table[i] != null && !table[i].removed)
            {
                tempTable[i] = table[i];
                remove((E) table[i].getItem());
            }
        }
    }

    public E procurar(E item)
    {
        int pos = Math.abs(item.hashCode() % this.hashSize);
        int counter = 0;

        while(counter < maxDifference)
        {
            if(table[pos] != null && !table[pos].removed && table[pos].getItem().equals(item))
            {
                return item;
            }
            pos++;
            counter++;
            if(pos > this.hashSize - 1)
            {
                pos = 0;
            }
        }
        if(table[pos] != null && table[pos].getItem().equals(item))
        {
            return item;
        }
        return null;
    }

    public void remove(E item)
    {
        table[procPos(item)].removed = true;
        occupied--;
    }

    public void insere(E item)
    {
        int pos = item.hashCode() % this.hashSize;
        Elemento newElement = new Elemento(item);
        int newPos = procPos(item);
        table[newPos] = newElement;
        occupied++;

        if(newPos - pos > maxDifference)
        {
            maxDifference = newPos - pos;
        }

        if(fatorCarga() > 0.5)
        {
            rehash();
        }
    }

    public void rehash()
    {
        int oldSize = this.hashSize;
        tornarVazia();
        int newSize = nextPrim(oldSize * 2);
        alocarTabela(newSize);

        for(int i = 0; i < oldSize; i++)
        {
            if(tempTable[i] != null && !tempTable[i].removed)
            {
                insere((E) tempTable[i].getItem());
            }
        }
    }

    public void print()
    {
        int counter = 0;

        System.out.println("Elementos na HashTable:");

        while(counter < this.hashSize)
        {
            Elemento elemento = table[counter];

            if(elemento != null && !elemento.removed)
            {
                System.out.println(procPos((E) elemento.getItem()) + " -> " + elemento.getItem());
            }
            counter++;
        }
    }
}


