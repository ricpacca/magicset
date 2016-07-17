package com.github.ricpacca.magicset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created on 17/07/16.
 */
public class MagicHashSetTest {
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
    public void sizeWhenEmptyTest() throws Exception {
        Assert.assertEquals(new MagicHashSet<TestUniqueItem>().size(), 0);
    }

    @Test
    public void sizeTest() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);
        set.add(item3);

        Assert.assertEquals(set.size(), 3);
    }

    @Test
    public void isEmptyShouldReturnTrueTest() throws Exception {
        Assert.assertTrue(new MagicHashSet<TestUniqueItem>().isEmpty());
    }

    @Test
    public void isEmptyShouldReturnFalseTest() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);

        Assert.assertFalse(set.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullItemShouldReturnFalse() throws Exception {
        new MagicHashSet<TestUniqueItem>().add(null);
    }

    @Test
    public void clearTest() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.clear();

        Assert.assertTrue(set.isEmpty());
        Assert.assertFalse(set.containsId(item1.getId()));
    }

    @Test
    public void getFromId() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);
        set.add(item3);

        Assert.assertTrue(set.getFromId(item1.getId()) == item1);
        Assert.assertTrue(set.containsId(item1.getId()));

        Assert.assertTrue(set.getFromId(item3.getId()) == item3);
        Assert.assertTrue(set.containsId(item3.getId()));
    }

    @Test
    public void getFromIds() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);
        set.add(item3);

        Set<TestUniqueItem> expected = new HashSet<>();
        expected.add(item1);
        expected.add(item3);

        Set<UUID> input = new HashSet<>();
        input.add(item1.getId());
        input.add(item3.getId());

        Assert.assertEquals(set.getFromIds(input), expected);
        Assert.assertTrue(set.containsIds(input));
    }

    @Test
    public void popFromId() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);
        set.add(item3);

        Assert.assertTrue(set.popFromId(item1.getId()) == item1);
        Assert.assertFalse(set.containsId(item1.getId()));

        Assert.assertTrue(set.popFromId(item3.getId()) == item3);
        Assert.assertFalse(set.containsId(item3.getId()));
    }

    @Test
    public void popFromIds() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);
        set.add(item3);

        Set<TestUniqueItem> expected = new HashSet<>();
        expected.add(item1);
        expected.add(item3);

        Set<UUID> input = new HashSet<>();
        input.add(item1.getId());
        input.add(item3.getId());

        Assert.assertEquals(set.popFromIds(input), expected);
        Assert.assertFalse(set.containsIds(input));
    }

    @Test
    public void containsId() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);

        Assert.assertTrue(set.containsId(item1.getId()));
    }

    @Test
    public void containsIdWhenAbsent() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);

        Assert.assertFalse(set.containsId(item3.getId()));
    }

    @Test
    public void containsIdWithNull() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);

        Assert.assertFalse(set.containsId(null));
    }

    @Test
    public void containsIds() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);
        set.add(item3);

        Set<UUID> input = new HashSet<>();
        input.add(item2.getId());
        input.add(item3.getId());

        Assert.assertTrue(set.containsIds(input));
    }

    @Test
    public void containsIdsWhenOneAbsent() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item3);

        Set<UUID> input = new HashSet<>();
        input.add(item2.getId());
        input.add(item3.getId());

        Assert.assertFalse(set.containsIds(input));
    }

    @Test
    public void containsIdsWhenAllAbsent() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);

        Set<UUID> input = new HashSet<>();
        input.add(item2.getId());
        input.add(item3.getId());

        Assert.assertFalse(set.containsIds(input));
    }

    @Test(expected = NullPointerException.class)
    public void containsIdsWithNull() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);

        set.containsIds(null);
    }

    @Test
    public void constructorWithCollectionTest() throws Exception {
        Collection<TestUniqueItem> c = new HashSet<>();
        c.add(item1);
        c.add(item2);
        c.add(item3);

        MagicSet<TestUniqueItem> set = new MagicHashSet<>(c);

        Set<UUID> input = new HashSet<>();
        input.add(item1.getId());
        input.add(item2.getId());
        input.add(item3.getId());

        Assert.assertTrue(set.containsIds(input));
        Assert.assertEquals(set.size(), 3);
    }

    @Test
    public void removeFromId() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);
        set.add(item3);

        set.removeFromId(item1.getId());
        Assert.assertFalse(set.containsId(item1.getId()));
        Assert.assertTrue(set.containsId(item2.getId()));
        Assert.assertTrue(set.containsId(item3.getId()));
    }

    @Test
    public void removeFromIds() throws Exception {
        MagicSet<TestUniqueItem> set = new MagicHashSet<>();
        set.add(item1);
        set.add(item2);
        set.add(item3);

        Set<UUID> input = new HashSet<>();
        input.add(item2.getId());
        input.add(item3.getId());

        set.removeFromIds(input);
        Assert.assertTrue(set.containsId(item1.getId()));
        Assert.assertFalse(set.containsId(item2.getId()));
        Assert.assertFalse(set.containsId(item3.getId()));
    }
}