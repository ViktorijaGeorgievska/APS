public class CBHT<K extends Comparable<K>, E> {
    private SLLNode<MapEntry<K, E>> []buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    // Translate key to an index of the array buckets.
    private int hash(K key) {
        // for Apteka
        // String keyString = (String) key;
        // return ((100 * (100 * (100 * 0 + keyString.charAt(2)) + keyString.charAt(1)) + keyString.charAt(0)) % 656565) % buckets.length;

        // String keyString = (String) key;
        // return  (100 * keyString.toUpperCase().charAt(0) + keyString.toUpperCase().charAt(1)) % 9091;

        return Math.abs(key.hashCode()) % buckets.length;
    }

    // Find which, if any node of this CBHT contains an entry whose key is equal to targetKey.
    // Return a link to that node (or null if there is none).
    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        int b = hash(targetKey);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];

        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(targetKey))
                return currNode;
            else
                currNode = currNode.succ;
        }
        return null;
    }

    // Insert the entry <key, val> into this CBHT.
    // If entry with same key exists, overwrite it.
    public void insert(K key, E value) {
        MapEntry<K, E> newEntry = new MapEntry<>(key, value);
        int b = hash(key);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];

        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                // Make newEntry replace the existing entry ...
                currNode.element = newEntry;
                return;
            } else {
                currNode = currNode.succ;
            }
        }
        // Insert newEntry at the front of the SLL in bucket b ...
        buckets[b] = new SLLNode<>(newEntry, buckets[b]);
    }
        // for BirthdaysLessDifficult
//        while (currNode != null) {
//            MapEntry<K, E> currEntry = currNode.element;
//            if (currEntry.value.equals(value)) {
//                return;
//            }
//            else {
//                currNode = currNode.succ;
//            }
//        }
//
//        if (buckets[b] == null) {
//            buckets[b] = new SLLNode<>(newEntry, buckets[b]);
//        } else {
//            SLLNode<MapEntry<K, E>> current = buckets[b];
//            while (current.succ != null) {
//                current = current.succ;
//            }
//            current.succ = new SLLNode<>(newEntry, null);
//        }

    // for BirthdaysMoreDifficult
    // vaka se vnesuvaat: Dragan Ana Bojan Zoran
    public void insertBirthdays(K key, E value) {
        MapEntry<K, E> newEntry = new MapEntry<>(key, value);
        int b = hash(key);
        SLLNode<MapEntry<K, E>> pred = null;                          // sekogash pred curr

        if (buckets[b] == null) {
            buckets[b] = new SLLNode<>(newEntry, buckets[b]);
            return;
        }

        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            Employee e = (Employee) curr.element.value;              // Dragan
            Employee nov = (Employee) value;

            if (e.name.compareTo(nov.name) > 0) {                    // Ana Dragan -> insertFirst so samo eden element vo listata
                if (curr == buckets[b]) {                            // samo edno ime ima vo listata
                    buckets[b] = new SLLNode<>(newEntry, buckets[b]);
                    return;
                }
                else {                                               // povekje lugje ima vo listata, vmetni in the middle -> Ana Bojan Dragan
                    pred.succ = new SLLNode<>(newEntry, curr);
                    return;
                }
            }
        }
        pred.succ = new SLLNode<>(newEntry, null);             // vmenti na kraj -> Ana Bojan Dragan Zoran
    }


    // Delete the entry (if any) whose key is equal to key from this CBHT.
    public void delete(K key) {
        int b = hash(key);
        SLLNode<MapEntry<K, E>> predNode = null, currNode = buckets[b];

        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                if (predNode == null) {
                    buckets[b] = currNode.succ;
                }
                else {
                    predNode.succ = currNode.succ;
                    return;
                }
            }
            else {
                predNode = currNode;
                currNode = currNode.succ;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";

            SLLNode<MapEntry<K, E>> curr = buckets[i];
            while (curr != null) {
                temp += curr.element.toString() + " ";
                curr = curr.succ;
            }
            temp += "\n";
        }
        return temp;
    }
}
