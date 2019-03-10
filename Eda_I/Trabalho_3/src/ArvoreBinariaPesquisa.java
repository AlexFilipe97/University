public class ArvoreBinariaPesquisa<E extends Comparable<? super E>>
{
    BNode<E> root;

    static class BNode<E>
    {
        E element;
        BNode<E> leftChild;
        BNode<E> rightChild;

        public BNode()
        {
            element = null;
            leftChild = null;
            rightChild = null;
        }

        public BNode(E element)
        {
            this.element = element;
            leftChild = null;
            rightChild = null;
        }

        public BNode(E element, BNode<E> leftChild, BNode<E> rightChild)
        {
            this.element = element;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public void setLeftChild(BNode<E> leftChild)
        {
            this.leftChild = leftChild;
        }

        public void setRightChild(BNode<E> rightChild)
        {
            this.rightChild = rightChild;
        }

        public void setElement(E element)
        {
            this.element = element;
        }

        public E getElement() { return element; }

        public BNode<E> getLeftChild()
        {
            return leftChild;
        }

        public BNode<E> getRightChild()
        {
            return rightChild;
        }
    }

    public ArvoreBinariaPesquisa()
    {
        root = new BNode<E>();
    }

    public ArvoreBinariaPesquisa(E element)
    {
        root = new BNode<E>(element);
    }

    public boolean isEmpty()
    {
        if(root != null)
        {
            return root.getElement() == null;
        }
        return true;
    }

    public boolean contains(E element)
    {
        BNode<E> currentNode = root;

        while(element != currentNode.getElement())
        {
            if(element.compareTo(currentNode.getElement()) < 0 && currentNode.getLeftChild() != null)
            {
                currentNode = currentNode.getLeftChild();
            }
            else if(element.compareTo(currentNode.getElement()) > 0 && currentNode.getRightChild() != null)
            {
                currentNode = currentNode.getRightChild();
            }
            else
            {
                break;
            }
        }
        if(element != currentNode.getElement())
        {
            System.out.println("O elemento não está na árvore.");
            return false;
        }
        return true;
    }

    public E findMin()
    {
        BNode<E> currentNode = root;

        while(currentNode.getLeftChild() != null)
        {
            currentNode = currentNode.getLeftChild();
        }
        return currentNode.getElement();
    }

    public E findMin(BNode<E> node)
    {
        BNode<E> currentNode = node;

        while(currentNode.getLeftChild() != null)
        {
            currentNode = currentNode.getLeftChild();
        }
        return currentNode.getElement();
    }

    public E findMax()
    {
        BNode<E> currentNode = root;

        while(currentNode.getRightChild() != null)
        {
            currentNode = currentNode.getRightChild();
        }
        return currentNode.getElement();
    }

    public E findMax(BNode<E> node)
    {
        BNode<E> currentNode = node;

        while(currentNode.getRightChild() != null)
        {
            currentNode = currentNode.getRightChild();
        }
        return currentNode.getElement();
    }

    public void insere(E element)
    {
        BNode<E> newNode = new BNode<E>(element);
        BNode<E> currentNode = root;

        if(isEmpty())
        {
            root = newNode;
        }
        else
        {
            while(true)
            {
                if(element.compareTo(currentNode.getElement()) < 0)
                {
                    if(currentNode.getLeftChild() == null)
                    {
                        currentNode.setLeftChild(newNode);
                        return;
                    }
                    else
                    {
                        currentNode = currentNode.getLeftChild();
                    }
                }
                else if(element.compareTo(currentNode.getElement()) > 0)
                {
                    if(currentNode.getRightChild() == null)
                    {
                        currentNode.setRightChild(newNode);
                        return;
                    }
                    else
                    {
                        currentNode = currentNode.getRightChild();
                    }
                }
            }

        }
    }

    public void remove(E element)
    {
        if(!contains(element))
        {
            return;
        }
        else
        {
            root = remove(element, root);
        }
    }

    public BNode<E> remove(E element, BNode<E> currentNode)
    {
        if(element.compareTo(currentNode.getElement()) < 0 && currentNode.getLeftChild() != null)
        {
            currentNode.leftChild = remove(element, currentNode.getLeftChild());
        }
        else if(element.compareTo(currentNode.getElement()) > 0 && currentNode.getRightChild() != null)
        {
            currentNode.rightChild = remove(element, currentNode.getRightChild());
        }
        else
        {
            if(currentNode.getLeftChild() == null)
            {
                return currentNode.getRightChild();
            }
            else if(currentNode.getRightChild() == null)
            {
                return currentNode.getLeftChild();
            }
            currentNode.element = findMin(currentNode.getRightChild());
            currentNode.rightChild = remove(currentNode.getElement(), currentNode.getRightChild());
        }
        return currentNode;
    }

    public void printEmOrdem()
    {
        printEmOrdem(root);
    }

    public void printEmOrdem(BNode<E> currentNode)
    {
        if(currentNode != null)
        {
            printEmOrdem(currentNode.getLeftChild());
            System.out.print(currentNode.getElement() + " ");
            printEmOrdem(currentNode.getRightChild());
        }
        /*
        if(currentNode.getLeftChild() != null)
        {
            printEmOrdem(currentNode.getLeftChild());
        }
        System.out.print(currentNode.getElement() + " ");

        if(currentNode.getRightChild() != null)
        {
            printEmOrdem(currentNode.getRightChild());
        }
        */
    }

    public void printPosOrdem()
    {
        printPosOrdem(root);
    }

    public void printPosOrdem(BNode<E> currentNode)
    {
        if(currentNode.getLeftChild() != null)
        {
            printPosOrdem(currentNode.getLeftChild());
        }

        if(currentNode.getRightChild() != null)
        {
            printPosOrdem(currentNode.getRightChild());
        }
        System.out.print(currentNode.getElement() + " ");
    }

    public void printPreOrdem()
    {
        printPreOrdem(root);
    }

    public void printPreOrdem(BNode<E> currentNode)
    {
        System.out.print(currentNode.getElement() + " ");

        if(currentNode.getLeftChild() != null)
        {
            printPreOrdem(currentNode.getLeftChild());
        }

        if(currentNode.getRightChild() != null)
        {
            printPreOrdem(currentNode.getRightChild());
        }
    }
}
