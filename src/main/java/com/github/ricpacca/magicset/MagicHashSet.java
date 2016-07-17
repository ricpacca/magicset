package com.github.ricpacca.magicset;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * NB: doesn't allow NULL values.
 *
 * @param <E> the type of elements maintained by this set
 * @author  Riccardo Paccagnella
 */
public class MagicHashSet<E extends UniqueItem> extends AbstractSet<E> implements MagicSet<E>, Serializable {
    private static final long serialVersionUID = 4671610434356171257L;

    private transient HashMap<UUID, E> map;

    /**
     * Constructs a new, empty magic hash set, with the default initial
     * capacity (16) and load factor (0.75).
     */
    public MagicHashSet() {
        map = new HashMap<>();
    }

    /**
     * Constructs a new, empty magic linked hash set, with the specified initial
     * capacity and the default load factor (0.75).
     */
    public MagicHashSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    /**
     * Constructs a new, empty magic hash set, with the default load factor (0.75),
     * containing all the elements in the specified collection.
     *
     * @param c the collection whose elements are to be placed into this set
     */
    public MagicHashSet(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }

    /**
     * Constructs a new, empty linked magic hash set, with the default initial
     * capacity (16) and load factor (0.75). (This package private
     * constructor is only used by magic linked hashset.)
     *
     * @param linked ignored (distinguishes this constructor from others.)
     */
    MagicHashSet(boolean linked) { // NOSONAR: see above
        map = new LinkedHashMap<>();
    }

    /**
     * Constructs a new, empty linked magic hash set, with the specified initial
     * capacity and the default load factor (0.75). (This package private
     * constructor is only used by magic linked hashset.)
     *
     * @param initialCapacity the initial capacity of the magic linked hash set
     * @param linked ignored (distinguishes this constructor from others.)
     */
    MagicHashSet(int initialCapacity, boolean linked) { // NOSONAR: see above
        map = new LinkedHashMap<>(initialCapacity);
    }

    @Override
    public Iterator<E> iterator() {
        return map.values().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean add(E e) {
        if(e == null)
            throw new IllegalArgumentException("Cannot add a null element to a Magic Set");

        return map.put(e.getId(), e) == null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    private void writeObject(java.io.ObjectOutputStream s) throws IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out size
        s.writeInt(map.size());

        // Write out all elements in the proper order.
        for (E e : map.values())
            s.writeObject(e);
    }

    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();

        // Read size and verify non-negative.
        int size = s.readInt();
        if (size < 0) {
            throw new InvalidObjectException("Illegal size: " +
                    size);
        }

        // Create backing HashMap
        map = new HashMap<>();

        map = (this) instanceof LinkedMagicHashSet ?
                new LinkedHashMap<>() :
                new HashMap<>();

        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            @SuppressWarnings("unchecked")
            E e = (E) s.readObject();
            map.put(e.getId(), e);
        }
    }

    @Override
    public E getFromId(UUID id) {
        return map.get(id);
    }

    @Override
    public Set<E> getFromIds(Collection<UUID> ids) {
        return ids.stream().map(this::getFromId).filter(e -> e != null).collect(Collectors.toSet());
    }

    @Override
    public E popFromId(UUID id) {
        return map.remove(id);
    }

    @Override
    public Set<E> popFromIds(Collection<UUID> ids) {
        return ids.stream().map(this::popFromId).filter(e -> e != null).collect(Collectors.toSet());
    }

    @Override
    public boolean containsId(UUID id) {
        return map.containsKey(id);
    }

    @Override
    public boolean containsIds(Collection<UUID> ids) {
        return ids.stream().allMatch(this::containsId);
    }

    @Override
    public boolean removeFromId(UUID id) {
        E removed = map.get(id);
        return map.remove(id) == removed;
    }

    @Override
    public boolean removeFromIds(Collection<UUID> ids) {
        int i = size();
        ids.forEach(this::removeFromId);
        return size() != i;
    }
}
