package com.github.ricpacca.magicset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created on 17/07/16.
 */
public class LinkedMagicHashSetTest {
    private TestUniqueItem item1;
    private TestUniqueItem item2;
    private TestUniqueItem item3;

    @Before
    public void setUp() throws Exception {
        item1 = new TestUniqueItem();
        item2 = new TestUniqueItem();
        item3 = new TestUniqueItem();
    }

    @Test
    public void iterator() throws Exception {
        MagicSet<TestUniqueItem> set = new LinkedMagicHashSet<>();
        set.add(item1);
        set.add(item2);
        set.add(item3);

        Iterator<TestUniqueItem> iterator = set.iterator();
        Assert.assertEquals(iterator.next(), item1);
        Assert.assertEquals(iterator.next(), item2);
        Assert.assertEquals(iterator.next(), item3);
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void constructorWithCollectionTest() throws Exception {
        Collection<TestUniqueItem> c = new HashSet<>();
        c.add(item1);
        c.add(item2);
        c.add(item3);

        MagicSet<TestUniqueItem> set = new LinkedMagicHashSet<>(c);

        Set<UUID> input = new HashSet<>();
        input.add(item1.getId());
        input.add(item2.getId());
        input.add(item3.getId());

        Assert.assertTrue(set.containsIds(input));
        Assert.assertEquals(set.size(), 3);
    }
}