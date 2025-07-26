public class MapEntry<K extends Comparable<K>, E> {
    K key;
    E value;

    public MapEntry(K key, E value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}