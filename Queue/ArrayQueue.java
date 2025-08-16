import java.util.NoSuchElementException;

class ArrayQueue<E> {
    // Редицата е претставена на следниот начин:
    // length го содржи бројот на елементи.
    // Ако length > 0, тогаш елементите на редицата се зачувани во elems[front...rear-1]
    // Ако rear > front, тогаш во elems[front...maxlength-1] и elems[0...rear-1]

    E[] elems;
    int length, front, rear;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty() {
        // Враќа true ако и само ако редицата е празна.
        return (length == 0);
    }

    public int size() {
        // Jа враќа должината на редицата.
        return length;
    }

    public E peek() {
        // Го враќа елементот на почетокот на редицата.
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public void clear() {
        // Jа празни редицата.
        length = 0;
        front = rear = 0;  // произволно
    }

    public void enqueue(E x) {
        // Го додава x на крај на редицата.
        if (length == elems.length)
            throw new NoSuchElementException();
        elems[rear++] = x;
        if (rear == elems.length) rear = 0;
        length++;
    }

    public E dequeue() {
        // Го отстранува и враќа почетниот елемент на редицата.
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length) front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}

