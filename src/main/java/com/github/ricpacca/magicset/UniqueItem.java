package com.github.ricpacca.magicset;

import java.util.UUID;

/**
 * Class providing an UUID to all its instances.
 *
 * @author  Riccardo Paccagnella
 */
public abstract class UniqueItem {
    private final UUID id;

    public UniqueItem() {
        this.id = UUID.randomUUID();
    }

    public UniqueItem(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("Unique items need to have a non-null UUID");
        
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }
}
