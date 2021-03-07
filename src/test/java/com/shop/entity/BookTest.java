package com.shop.entity;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class BookTest extends TestCase {

    @Test
    public void testNewBuilder() {

        Book book = Book.newBuilder().addAuthor("Test").addImage("123123").build();

        Assert.assertEquals("Test", book.getAuthor());
        Assert.assertEquals(null, book.getTitle());

    }
}