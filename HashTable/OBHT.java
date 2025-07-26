public class OBHT <K extends Comparable<K>, E> {
    private MapEntry<K, E> [] buckets;

    // buckets[b] е null ако „кофичката” b не била никогаш зафатена.
    // buckets[b] е претходно зафатена ако во „кофичката” b имало претходно елемент кој е
    // избришан и моментално нема елемент во оваа „кофичка”.
    // Го означуваме со former објектот кој е даден подолу

    static final int NONE = -1;   //различно од било кој индекс на „кофичка”

    private static final MapEntry former = new MapEntry(null, null);   // Ова гарантира дека за било кој елемент e, e.key.equals(former.key) е false.

    private int occupancy = 0 ;                                        // број на зафатени или претходно зафатени „кофички”.

    public OBHT(int m) {
        buckets = (MapEntry<K, E>[]) new MapEntry[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public int search(K targetKey) {
        int b = hash(targetKey);
        for (int n_search = 0; n_search < buckets.length; n_search++) {
            MapEntry<K, E> currEntry = buckets[b];

            if (currEntry == null)
                return NONE;
            else if (currEntry.key.equals(targetKey))
                return b;
            else
                b = (b + 1) % buckets.length;
        }
        return NONE;
    }

    @SuppressWarnings("unchecked")
    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this OBHT.
        int b = hash(key);
        int n_search = 0;

        while (n_search < buckets.length) {
            MapEntry<K, E> currEntry = buckets[b];

            if (currEntry == null)
                return;
            else if (currEntry.key.equals(key)) {
                buckets[b] = former;
                return;
            }
            else {
                b = (b + 1) % buckets.length;
                n_search++;
            }
        }
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<>(key, val);
        int b = hash(key);
        int n_search = 0;

        for (;;) {
            MapEntry<K, E> oldEntry = buckets[b];

            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash table is full!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                if (oldEntry == former) {
                    occupancy++;
                }
                return;
            } else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length) {
                    return;
                }
            }
        }
    }

    public String toString() {
        String tmp = "";
        for (int i = 0; i < buckets.length; i++) {
            tmp += i + ":";
            if (buckets[i] == null)
                tmp += "\n";
            else if (buckets[i] == former)
                tmp += "former \n";
            else
                tmp += buckets[i] + "\n";
        }
        return tmp;
    }

    public OBHT<K, E> clone() {
        OBHT<K, E> copy = new OBHT<>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K, E> e = buckets[i];
            if (e != null && e != former)
                copy.buckets[i] = new MapEntry<K, E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }

    public MapEntry<K, E> getMap(int b) {
        return buckets[b];
    }

    public MapEntry<K,E> getBucket(int search) {
        return buckets[search];
    }
}
