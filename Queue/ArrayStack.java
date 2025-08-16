import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        return depth == 0;
    }

    public E peek() {
        if (depth == 0) {
            throw new NoSuchElementException();
        }
        return elems[depth - 1];
    }

    public void clear() {
        for (int i = 0; i < depth; i++) {
            elems[i] = null;
        }
        depth = 0;
    }

    public void push(E x) {
        elems[depth++] = x;
    }

    public int size() {
        return depth;
    }

    public E pop() {
        if (depth == 0) {
            throw new NoSuchElementException();
        }
        E topMost = elems[--depth];
        elems[depth] = null;
        return topMost;
    }
}
