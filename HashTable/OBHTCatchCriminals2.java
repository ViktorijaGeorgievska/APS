import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
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

Output:
IvanIvanovski
*/

class DNK implements Comparable<DNK> {
    String firstDNK;
    String secondDNK;

    public DNK(String dnk1, String dnk2) {
        this.firstDNK = dnk1;
        this.secondDNK = dnk2;
    }

    @Override
    public int compareTo(DNK o) {
        int cmpFirst = this.firstDNK.compareTo(o.secondDNK);
        if (cmpFirst != 0)
            return cmpFirst;
        return this.secondDNK.compareTo(o.firstDNK);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof DNK))
            return false;
        DNK other = (DNK) obj;
        return (this.firstDNK.equals(other.firstDNK) && this.secondDNK.equals(other.secondDNK));
    }

    @Override
    public int hashCode() {
        return firstDNK.hashCode() * 31 + secondDNK.hashCode();
    }
}

public class OBHTCatchCriminals2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));

        int n = Integer.parseInt(input.readLine());
        OBHT<DNK, String> hashTable = new OBHT<>(2 * n - 1);
        for (int i = 0; i < n; i++) {
            String name = input.readLine();
            String dnk1 = input.readLine();
            String dnk2 = input.readLine();
            DNK d = new DNK(dnk1, dnk2);
            hashTable.insert(d, name);
        }

        String first = input.readLine();
        String second = input.readLine();
        DNK searchDNK = new DNK(first, second);

        if (hashTable.search(searchDNK) != -1) {
            MapEntry<DNK, String> result = hashTable.getBucket(hashTable.search(searchDNK));
            System.out.println(result.value);
        } else
            System.out.println("Unknown");
    }
}
