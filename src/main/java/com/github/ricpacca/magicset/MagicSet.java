package com.github.ricpacca.magicset;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * Interface defining the functionalities of a MagicSet.
 *
 * @param <E> the type of elements maintained by this set
 * @author  Riccardo Paccagnella
 */
public interface MagicSet<E extends UniqueItem> extends Set<E> {

    /**
     * Returns true if this MagicSet contains a unique component with the specified UUID.
     *
     * @param id the UUID of the unique component whose presence in this MagicSet is to be tested
     * @return true if a unique component with the specified UUID is present in this MagicSet
     */
    boolean containsId(UUID id);

    /**
     * Returns true if this MagicSet contains all the unique components with the specified UUIDs.
     *
     * @param ids the Collection of UUIDs of the unique components whose presence in this MagicSet is to be tested
     * @return true if all the unique components with the specified UUIDs are present in this MagicSet
     */
    boolean containsIds(Collection<UUID> ids);

    /**
     * Returns the unique component with the specified UUID in this MagicSet.
     * Returns null if the specified UUID does not belong to any unique component.
     *
     * @param id the UUID of the element to return
     * @return the unique component with the specified UUID in this MagicSet, or null if the not present
     */
    E getFromId(UUID id);

    /**
     * Returns a Set of the unique components with the specified UUIDs in this MagicSet.
     * Should any of the provided UUIDs be not present in the MagicSet, that UUID will be ignored.
     *
     * @param id the Collection of UUIDs of the unique components to return
     * @return the Set of unique components with the specified UUIDs in this MagicSet
     */
    Collection<E> getFromIds(Collection<UUID> id);

    /**
     * Removes the unique component with the specified UUID from this MagicSet if it is present.
     * Returns true if this set contained that element.
     *
     * @param id the UUID of the unique component to remove
     * @return true if the specified UUID was in removed from the MagicSet
     */
    boolean removeFromId(UUID id);

    /**
     * Removes the unique components with the UUIDs of the given Collection from this MagicSet.
     * Should any of the provided UUIDs be not present in the MagicSet, that UUID will be ignored.
     *
     * @param ids the Collection of UUIDs of the unique components to remove
     * @return true if this MagicSet changed as a result of the call
     */
    boolean removeFromIds(Collection<UUID> ids);

    /**
     * Removes and returns (pops) the unique component with the specified UUID in this MagicSet.
     * Returns null if the specified UUID does not belong to any unique component.
     *
     * @param id the UUID of the element to pop
     * @return the unique component with the specified UUID in this MagicSet, or null if the not present
     */
    E popFromId(UUID id);

    /**
     * Removes and returns (pops) the Collection of the unique components with the specified UUIDs in this MagicSet.
     * Should any of the provided UUIDs be not present in the MagicSet, that UUID will be ignored.
     *
     * @param ids the Collection of UUIDs of the unique components to pop
     * @return the Set of unique components with the specified UUIDs in this MagicSet
     */
    Collection<E> popFromIds(Collection<UUID> ids);
}