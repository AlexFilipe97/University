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

        public String toString()
        {
            return nome + "----->" + numero[0] + " | " + numero[1];
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
        contactos.printEmOrdem();
        //listar(contactos.root);
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