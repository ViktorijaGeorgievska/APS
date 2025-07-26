import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
    private SLLNode<E> top;
    int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    public String toString() {
        SLLNode<E> current = top;
        StringBuilder s = new StringBuilder();
        while (current != null) {
            s.append(current.element);
            s.append(" ");
            current = current.succ;
        }
        return s.toString();
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public E peek() {
        if (top == null) {
            throw new NoSuchElementException();
        }
        return top.element;
    }

    public void push(E x) {
        top = new SLLNode<>(x, top);
        size++;
    }

    public int size() {
        return size;
    }

    public E pop() {
        if (top == null) {
            throw new NoSuchElementException();
        }
        E topElem = top.element;
        size--;
        top = top.succ;
        return topElem;
    }
}
