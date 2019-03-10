package edaI_T2;

public class LineEditor
{
    int cursor;
    DoubleLinkedList<String> lista;

    public LineEditor()
    {
        cursor = 0;
        lista = new DoubleLinkedList<String>();
    }

    public void insertEnd(String text)
    {
        if(lista.is_empty() || cursor == lista.size())
        {
            lista.add(text);
            cursor++;
        }
        else
        {
            lista.add(text);
        }
    }

    public void insert(String text)
    {
        if(lista.is_empty())
        {
            insertEnd(text);
        }
        else
        {
            lista.add(cursor, text);
        }
    }

    public void insert(int pos, String text)
    {
        if(lista.is_empty())
        {
            insertEnd(text);
        }
        else if(cursor == lista.size())
        {
            lista.add(pos, text);

            if(cursor != lista.size())
            {
                cursor++;
            }
        }
        else
        {
            lista.add(pos, text);
        }
    }

    public void delete()
    {
        if(lista.size() == cursor)
        {
            lista.remove(cursor);
            cursor--;
        }
        else
        {
            lista.remove(cursor);
        }
    }

    public void delete(int pos)
    {
        if(cursor != lista.size())
        {
            lista.remove(pos);
        }
        else
        {
            lista.remove(pos);
            cursor--;
        }
    }

    public void edit(String text)
    {
        lista.set(cursor, text);
    }

    public void edit(int pos, String text)
    {
        lista.set(pos, text);
    }

    public void print()
    {
        System.out.println(lista.toString());
        System.out.println("cursor -> " + cursor);
    }

    public void search(String text)
    {
        System.out.println(lista.find(text));
    }

    public void lineUp()
    {
        if(cursor == 1)
        {
            System.out.println("Não há mais linhas para cima.");
        }
        else
        {
            cursor--;
        }
    }

    public void lineDown()
    {
        if(cursor == lista.size())
        {
            System.out.println("Não há mais linhas para baixo.");
        }
        else
        {
            cursor++;
        }
    }
}
