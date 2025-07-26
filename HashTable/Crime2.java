import java.util.Scanner;

/*
APS book
3
IvanIvanovski
ACGTGTACCATGATAG
GGTACGATCCT
ElenaPetrevska
GTACCGATGCTAGGATC
ACGTAGCTCCGGATCG
KostaKostovski
CGCTAATTTAAAGC
TAGACTCGATCGCT
ACGTGTACCATGATAG
GGTACGATCCT
*/

class Criminal implements Comparable<Criminal> {
    String firstDNK;
    String secondDNK;

    public Criminal(String firstDNK, String secondDNK) {
        this.firstDNK = firstDNK;
        this.secondDNK = secondDNK;
    }

    @Override
    public int compareTo(Criminal o) {
        int cmpFirst = this.firstDNK.compareTo(o.firstDNK);
        if (cmpFirst != 0) {
            return cmpFirst;
        }
        return this.secondDNK.compareTo(o.secondDNK);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Criminal))
            return false;
        Criminal other = (Criminal) obj;
        return (this.firstDNK.equals(other.firstDNK) && this.secondDNK.equals(other.secondDNK));
    }
}

public class Crime2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        CBHT<Criminal, String> table = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String name = in.next();
            String dnk1 = in.next();
            String dnk2 = in.next();
            Criminal c = new Criminal(dnk1, dnk2);

            table.insert(c, name);
        }
        System.out.println(table);

        String first = in.next();
        String second = in.next();
        Criminal cSearch = new Criminal(first, second);

        SLLNode<MapEntry<Criminal, String>> search = table.search(cSearch);
        if (search != null) {
            System.out.println(search.element.value);
        } else {
            System.out.println("Unknown");
        }
    }
}
