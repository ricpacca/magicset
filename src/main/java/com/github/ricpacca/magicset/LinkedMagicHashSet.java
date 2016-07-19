package com.github.ricpacca.magicset;

import java.util.Collection;

/**
 * MagicHashSet with predictable iteration order.
 *
 * @param <E> the type of elements maintained by this set
 * @author  Riccardo Paccagnella
 */
public class LinkedMagicHashSet<E extends UniqueItem> extends MagicHashSet<E> {
    private static final long serialVersionUID = -4511779546048839898L;

    public LinkedMagicHashSet() {
        super(true);
    }

    public LinkedMagicHashSet(int initialCapacity) {
        super(initialCapacity, true);
    }

    public LinkedMagicHashSet(Collection<? extends E> c) {
        super(Math.max(2*c.size(), 11), true);
        addAll(c);
    }
}
