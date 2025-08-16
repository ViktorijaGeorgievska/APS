public interface Queue<E> {
    // Методи за пристап:
    // Враќа true ако и само ако редицата е празна.
    public boolean isEmpty();

    // Jа враќа должината на редицата.
    public int size();

    //  Jа враќа должината на редицата.
    public E peek();

    // Методи за трансформациjа:
    // Jа празни редицата.
    public void clear();

    // Го додава x на краj на редицата.
    public void enqueue(E x);

    // Го отстранува и враќа почетниот елемент на редицата.
    public E dequeue();
}
