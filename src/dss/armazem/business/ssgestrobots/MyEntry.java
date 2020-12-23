package dss.armazem.business.ssgestrobots;

import java.util.Map;

/**
 * Classe MyEntry.
 * Semelhante à Map.Entry, mas neste caso é possível criar e adicionar entradas a uma Collection,
 * por exemplo, enquanto que na Map.Entry não é possível.
 * @param <K> chave da entrada, que irá corresponder a uma String que identifica o vértice "pai" (antecessor)
 * do Mapa/Grafo.
 * @param <V> valor da entrada, que corresponderá à lista dos vértices sucessores da chave da entrada,
 * juntamente com os respetivos pesos das ligações.
 */
public class MyEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    /**
     * Construtor vazio
     * @param key chave
     */
    public MyEntry(final K key) {
        this.key = key;
    }

    /**
     * Construtor parametrizado
     * @param key chave
     * @param value valor
     */
    public MyEntry(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Getters e Setters
     */

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(final V value) {
        final V oldValue = this.value;
        this.value = value;
        return oldValue;
    }
}
