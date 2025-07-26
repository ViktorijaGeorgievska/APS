public class SLL<E> {
    SLLNode<E> first;

    public SLL () {
        this.first = null;            // konstruirame prazna lista
    }

    public void insertFirst (E element) {
        SLLNode<E> nov = new SLLNode<E>(element, first);
        first = nov;
    }

    public void insertLast (E element) {
        if (first == null) {          // ako e prazna listata
            insertFirst(element);
        }
        else {
            SLLNode<E> nov = new SLLNode<E>(element, null);
            SLLNode<E> current = first;
            while (current.succ != null) {
                current = current.succ;
            }
            current.succ = nov;
        }
    }
    public SLLNode<E> getFirst () {
        return first;
    }
    public int size () {
        int listSize = 0;
        SLLNode<E> current = first;
        while (current != null) {
            listSize++;
            current = current.succ;
        }
        return listSize;
    }
}
