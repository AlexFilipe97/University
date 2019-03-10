package edaI_T2;

public class DoubleLinkedList<T>
{
    Node<T> header = new Node<T>();
    Node<T> tail;
    Node<T> current;
    int size;
    public class AgendaTelemovel
    {
        static class Contacto implements Comparable<Contacto>
        {
            String nome;
            int[] numero = new int[] {-1, -1};
    
            public Contacto(String nome, int numero)
            {
                this.nome = nome;
    
                if(this.numero[0] != -1)
                {
                    this.numero[1] = numero;
                }
                else
                {
                    this.numero[0] = numero;
                }
            }
    
            public String getNome()
            {
                return nome;
            }
    
            public int[] getNumero()
            {
                return this.numero;
            }
    
            public void setNome(String nome)
            {
                this.nome = nome;
            }
    
            public void setNumero(int numero, int pos)
            {
                this.numero[pos] = numero;
            }
    
    
            public int compareTo(Contacto o)
            {
                return this.nome.compareTo(o.nome);
            }
        }
    
        ArvoreBinariaPesquisa<Contacto> contactos;
    
        public AgendaTelemovel()
        {
            contactos = new ArvoreBinariaPesquisa<Contacto>();
        }
    
        public void adicionar(Contacto novoContacto)
        {
            contactos.insere(novoContacto);
        }
    
        public void editar(Contacto pessoa, int novoNumero, int pos)
        {
            ArvoreBinariaPesquisa.BNode<Contacto> currentNode = contactos.root;
    
            while(pessoa.getNome() != currentNode.getElement().getNome())
            {
                if(pessoa.getNome().compareTo(currentNode.getElement().getNome()) < 0 && currentNode.getLeftChild() != null)
                {
                    currentNode = currentNode.getLeftChild();
                }
                else if(pessoa.getNome().compareTo(currentNode.getElement().getNome()) > 0 && currentNode.getRightChild() != null)
                {
                    currentNode = currentNode.getRightChild();
                }
                else
                {
                    break;
                }
            }
            currentNode.getElement().setNumero(novoNumero, pos);
        }
    
        public void editar(Contacto pessoa, String novoNome)
        {
            ArvoreBinariaPesquisa.BNode<Contacto> currentNode = contactos.root;
    
            while(pessoa.getNome() != currentNode.getElement().getNome())
            {
                if(pessoa.getNome().compareTo(currentNode.getElement().getNome()) < 0 && currentNode.getLeftChild() != null)
                {
                    currentNode = currentNode.getLeftChild();
                }
                else if(pessoa.getNome().compareTo(currentNode.getElement().getNome()) > 0 && currentNode.getRightChild() != null)
                {
                    currentNode = currentNode.getRightChild();
                }
                else
                {
                    break;
                }
            }
    
            currentNode.getElement().setNome(novoNome);
        }
    
        public void remover(Contacto pessoa)
        {
            contactos.remove(pessoa);
        }
    
        public void listar()
        {
            listar(contactos.root);
        }
    
        public void listar(ArvoreBinariaPesquisa.BNode<Contacto> currentNode)
        {
            if(currentNode.getLeftChild() != null)
            {
                listar(currentNode.getLeftChild());
            }
            System.out.print(currentNode.getElement().getNome() + " ");
    
            for(int i = 0; i < 2; i++)
            {
                if(currentNode.getElement().getNumero()[i] == -1)
                {
                    System.out.print("");
                }
                else
                {
                    System.out.print(currentNode.getElement().getNumero()[i] + " ");
                }
            }
    
            if(currentNode.getRightChild() != null)
            {
                listar(currentNode.getRightChild());
            }
        }
    
        public void chamador(int chamada)
        {
            chamador(contactos.root, chamada);
        }
    
        public void chamador(ArvoreBinariaPesquisa.BNode<Contacto> currentNode, int chamada)
        {
            if(currentNode.getElement().numero[0] != chamada && currentNode.getElement().numero[1] != chamada)
            {
                if(currentNode.getLeftChild() != null)
                {
                    chamador(currentNode.getLeftChild(), chamada);
                }
                if(currentNode.getRightChild() != null)
                {
                    chamador(currentNode.getRightChild(), chamada);
                }
                return;
            }
    
            if(currentNode.getElement().getNumero()[0] == chamada || currentNode.getElement().getNumero()[1] == chamada)
            {
                System.out.println(currentNode.getElement().getNome());
            }
            else
            {
                System.out.println("DESCONHEICDO");
            }
        }
    }
    
    
    static class Node<T>
    {
        T element;
        Node<T> next;
        Node<T> prev;

        public Node()
        {
            element = null;
            next = null;
            prev = null;
        }

        public Node(T x)
        {
            element = x;
            next = null;
            prev = null;
        }

        public Node(T x, Node<T> n)
        {
            element = x;
            next = n;
            prev = null;
        }

        public Node<T> getNext()
        {
            return next;
        }

        public Node<T> getPrev()
        {
            return prev;
        }

        public void setElement(T x)
        {
            element = x;
        }

        public T getElement()
        {
            return element;
        }

        public void setNext(Node<T> n)
        {
            next = n;
        }

        public void setPrev(Node<T> n)
        {
            prev = n;
        }
    }

    static class Iterador<T> implements java.util.Iterator<T>
    {
        private Node<T> current;

        public Iterador(Node<T> n)
        {
            current = n;
        }

        public boolean hasNext()
        {
            return current.getNext() != null;
        }

        public T next()
        {
            if(!hasNext())
            {
                throw new java.util.NoSuchElementException();
            }

            T next_element = current.getElement();
            current = current.getNext();
            return next_element;
        }

        public boolean hasPrevious()
        {
            return current.getPrev() != null;
        }

        public T previous()
        {
            if(!hasPrevious())
            {
                throw new java.util.NoSuchElementException();
            }

            T prev_element = current.getElement();
            current = current.getPrev();
            return prev_element;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public DoubleLinkedList()
    {
        tail = header;
        size = 0;
    }

    public DoubleLinkedList(T element)
    {
        current = new Node<T>(element);
        header.setNext(current);
        tail = current;
        current.setPrev(header);
        size++;
    }

    public DoubleLinkedList(T element, Node<T> node)
    {
        current = new Node<>(element, node);
        header.setNext(current);
        tail = node;
        current.setPrev(header);
        size++;
    }

    public Iterador iterator()
    {
        return new Iterador<T>(header.getNext());
    }

    public void remove(int pos)
    {
        if(pos > size)
        {
            System.out.println("Impossível chegar a essa posição");
        }
        else if(is_empty())
        {
            System.out.println("Lista vazia");
        }
        else
        {
            Node<T> nextNode = get(pos).getNext();
            Node<T> prevNode = get(pos).getPrev();

            if(nextNode == null)
            {
                prevNode.setNext(null);
                tail = prevNode;
            }
            else if(prevNode == null)
            {
                nextNode.setPrev(this.header);
            }
            else
            {
                prevNode.setNext(nextNode);
                nextNode.setPrev(prevNode);
            }
            size--;
        }
    }

    public void remove(T element)
    {
        Iterador iterator = iterator();
        int pos = 1;

        if(tail.getElement() == element)
        {
            remove(size);
        }
        else
        {
            while(iterator.hasNext())
            {
                if(iterator.next() != element)
                {
                    pos++;
                }
                else
                {
                    remove(pos);
                    break;
                }
            }
            //System.out.println("Não existe esse elemento.");
        }
    }

    public void add(T element)
    {
        Node<T> newNode = new Node<T>(element);

        if(is_empty())
        {
            header.setNext(newNode);
            newNode.setPrev(header);
            tail = newNode;
            size++;
        }
        else
        {

            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            size++;
        }
    }

    public void add(int pos, T element)
    {
        Node<T> newNode = new Node<T>(element);

        if(is_empty() || pos == size + 1)
        {
            add(element);
        }
        else
        {
            if(pos > size + 1)
            {
                System.out.println("Não pode inserir nessa posição");
            }
            else
            {
                Node<T> nodeNext = get(pos);
                Node<T> nodePrev = get(pos).getPrev();

                nodePrev.setNext(newNode);
                newNode.setPrev(nodePrev);
                newNode.setNext(nodeNext);
                nodeNext.setPrev(newNode);
                size++;
            }
        }
    }

    public Node get(int pos)
    {
        int counter = 0;
        Node<T> currentNode = header;

        if(pos == 0)
        {
            System.out.println("Não pode inserir nessa posição.");
        }
        else
        {
            while(counter != pos)
            {
                currentNode = currentNode.getNext();
                counter++;
            }
        }
        return currentNode;
    }

    public void set(int pos, T element)
    {
        if(is_empty())
        {
            add(element);
        }
        else
        {
            if(pos > size)
            {
                System.out.println("Não pode inserir nessa posição");
            }
            else
            {
                get(pos).setElement(element);
            }
        }
    }

    public String find(T element)
    {
        Iterador iterator = iterator();
        String stringTail = (String) tail.getElement();
        String text = (String) element;
        int counter = 1;

        if(stringTail.contains(text))
        {
            return size + " " + stringTail;
        }
        else
        {
            while(iterator.hasNext())
            {
                String stringIterator = (String) iterator.next();

                if(stringIterator.contains(text))
                {
                    return counter + " " + stringIterator;
                }
                else
                {
                    counter++;
                }
            }
        }
        return "Não existe esse elemento.";
    }

    public int size()
    {
        return size;
    }

    public boolean is_empty()
    {
        return size == 0;
    }

    public String toString()
    {
        int linha = 1;
        String ret = "";
        Iterador iterator = iterator();

        if(is_empty())
        {
            System.out.println("Lista vazia.");
        }
        else
        {
            while(iterator.hasNext())
            {
                ret += linha + " " + iterator.next() + "\n";
                linha++;
            }
            ret += linha + " " + tail.getElement();
            System.out.println();
        }
        return ret;
    }
}
