package com.github.ricpacca.magicset;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * Created on 17/07/16.
 */
public class UniqueItemTest {
    @Test(expected = IllegalArgumentException.class)
    public void settingNullIdShouldThrowException() throws Exception {
        new TestUniqueItem(null);
    }

    @Test
    public void settingCustomIdTest() throws Exception {
        UUID testId = UUID.randomUUID();
        UniqueItem item = new TestUniqueItem(testId);
        Assert.assertEquals(item.getId(), testId);
    }

    @Test
    public void settingRandomIdShouldSetIdsThatAreNotNull() throws Exception {
        UniqueItem item1 = new TestUniqueItem();
        UniqueItem item2 = new TestUniqueItem();
        UniqueItem item3 = new TestUniqueItem();

        Assert.assertNotNull(item1.getId());
        Assert.assertNotNull(item2.getId());
        Assert.assertNotNull(item3.getId());
    }

    @Test
    public void settingRandomIdShouldSetDifferentIds() throws Exception {
        UniqueItem item1 = new TestUniqueItem();
        UniqueItem item2 = new TestUniqueItem();
        UniqueItem item3 = new TestUniqueItem();

        Assert.assertNotEquals(item1.getId(), item2.getId());
        Assert.assertNotEquals(item1.getId(), item3.getId());
        Assert.assertNotEquals(item2.getId(), item3.getId());
    }
}