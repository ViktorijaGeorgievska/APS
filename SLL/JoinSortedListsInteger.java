import java.util.Scanner;

/*
Input
9
1 2 4 16 20 20 22 23 34
11
3 4 5 5 9 15 18 21 23 23 34
Output
1->2->3->4->5->9->15->16->18->20->21->22->23->34
*/

public class JoinSortedListsInteger {
    public static void deleteDuplicates(SLL<Integer> list) {
        SLLNode<Integer> current = list.getFirst();

        while (current != null) {
            SLLNode<Integer> helper = current.succ;

            while (helper != null) {
                if (helper.element == current.element) {
                    list.delete(helper);
                    break;
                }
                helper = helper.succ;
            }
            current = current.succ;
        }
    }

    public static SLL<Integer> joinSortedLists(SLL<Integer> list1, SLL<Integer> list2) {
        SLL<Integer> result ; // = new SLL<>();
        SLLNode<Integer> current1;
        SLLNode<Integer> current2;

        if (list1.getFirst().element < list2.getFirst().element) {
            result = list1;
            current1 = list1.getFirst();
            current2 = list2.getFirst();
        }
        else if (list1.getFirst().element > list2.getFirst().element) {
            result = list2;
            current1 = list2.getFirst();
            current2 = list1.getFirst();
        }
        else {
            result = list1;
            current1 = list1.getFirst();
            current2 = list2.getFirst();
        }

        while (current2 != null) {
            if (current1.succ != null) {
                if (current1.succ.element > current2.element) {
                    result.insertBefore(current2.element, current1.succ);
                    current1 = current1.succ;
                    current2 = current2.succ;
                }
                else if (current1.succ.element < current2.element) {
                    current1 = current1.succ;
                }
                else {
                    current1 = current1.succ;
                    current2 = current2.succ;
                }
            }
            else {
                while (current2 != null) {
                    result.insertLast(current2.element);
                    current2 = current2.succ;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SLL<Integer> list1 = new SLL<>();
        SLL<Integer> list2 = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            list1.insertLast(input.nextInt());
        }
        int m = input.nextInt();
        for (int i = 0; i < m; i++) {
            list2.insertLast(input.nextInt());
        }
        input.close();

        deleteDuplicates(list1);
        deleteDuplicates(list2);
        SLL<Integer> result = joinSortedLists(list1, list2);
        System.out.println(result);
    }
}
/*
public class Main
{
    public static void brisiDuplikati(SLL<Integer> lista)
    {

        SLLNode<Integer> dvizi  = lista.getFirst();
        while(dvizi.succ!=null) {
            if (dvizi.element != dvizi.succ.element) {
                dvizi = dvizi.succ;
            } else //duplikat
            {
                lista.delete(dvizi.succ);
            }
        }
    }
    public static SLL<Integer> joinLists(SLL<Integer> l1, SLL<Integer> l2)
    {
        SLL<Integer> result;
        SLLNode<Integer> d1;
        SLLNode<Integer> d2;
        if(l1.getFirst().element < l2.getFirst().element)
        {
            result = l1;
            d1 = l1.getFirst();
            d2 = l2.getFirst();
        }
        else if(l1.getFirst().element > l2.getFirst().element)
        {
            result =  l2;
            d1 = l2.getFirst();
            d2 = l1.getFirst();
        }
        else
        {
            result = l1;
            d1 = l1.getFirst();
            d2 = l2.getFirst();
        }

        while(d2!=null)
        {
            if(d1.succ!=null) {
                if ((Integer) d1.succ.element > d2.element) {
                    result.insertBefore(d2.element, d1.succ);
                    d1 = d1.succ;
                    d2 = d2.succ;
                } else if ((Integer) d1.succ.element < d2.element) {
                    d1 = d1.succ;
                } else {
                    d1 = d1.succ;
                    d2 = d2.succ;
                }
            }
            else
            {
                while(d2!=null)
                {
                    result.insertLast(d2.element);
                    d2 = d2.succ;
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int n,m;
        Scanner input= new Scanner(System.in);
        SLL<Integer> lista1 = new SLL();
        SLL<Integer> lista2 = new SLL();
        n = input.nextInt();
        for(int i=0;i<n;i++)
        {
            lista1.insertLast(input.nextInt());
        }
        m = input.nextInt();
        for(int i=0;i<m;i++)
        {
            lista2.insertLast(input.nextInt());
        }
        brisiDuplikati(lista1);
        brisiDuplikati(lista2);

        SLL<Integer> result = joinLists(lista1,lista2);
        System.out.println(result);
    }
}
*/
