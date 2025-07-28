class DLL<E> {
    private DLLNode<E> first;
    private DLLNode<E> last;

    public DLL() {
        this.first = null;              // konstruirame prazna lista
        this.last = null;
    }

    public void insertFirst(E element) {
        DLLNode<E> nov = new DLLNode<>(element, null, first);
        if (first == null) {
            last = nov;
//            first = nov;
        } else {
            first.pred = nov;
        }
        first = nov;             // ova se odnesuva i na if i na else
    }

    public void insertLast(E element) {
        if (first == null) {
            insertFirst(element);
        } else {
            DLLNode<E> nov = new DLLNode<>(element, last, null);
            last.succ = nov;
            last = nov;
        }
    }

    public void insertAfter(E element, DLLNode<E> after) {
        if (after == last) {
            insertLast(element);
            return;
        }
        DLLNode<E> nov = new DLLNode<>(element, after, after.succ);
        after.succ.pred = nov;
        after.succ = nov;
    }

    public void insertBefore(E element, DLLNode<E> before) {
        if (before == first) {
            insertFirst(element);
        }
        DLLNode<E> nov = new DLLNode<>(element, before.pred, before);
        before.pred.succ = nov;
        before.pred = nov;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) {
                first.pred = null;
            }
            if (first == null) {
                last = null;
            }
            return tmp.element;
        } else {
            return null;
        }
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null) {
                return deleteFirst();
            } else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else {
            return null;
        }
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;

        return node.element;
    }

    public int getSize() {
        int size = 0;
        DLLNode<E> tmp = first;
        while (tmp != null) {
            size++;
            tmp = tmp.succ;
        }
        return size;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else {
            ret = "Empty list!!!";
//            ret = "Prazna lista!!!";
        }
        return ret;
    }

    public void removeDuplicates() {
        if (first != null) {
            DLLNode<E> tmp = first;
            DLLNode<E> tmp2 = tmp.succ;

            while (tmp.succ != null) {
                while (tmp2 != null) {
                    if (tmp.element == tmp2.element) {
                        if (tmp2.succ != null) {
                            tmp2 = tmp2.succ;
                            this.delete(tmp2.pred);
                        } else {
                            this.delete(tmp2);
                            tmp2 = null;
                        }
                    } else {
                        tmp2 = tmp2.succ;
                    }
                }
                tmp = tmp.succ;
                if (tmp == null)     // koga ke dojdeme do kraj na tmp da ne odime na naredniot
                    break;
                tmp2 = tmp.succ;    // tmp2 pak da pokazuva kon naredniot posle tmp
            }
        }
    }
}