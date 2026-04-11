package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SingleLinkedListTest {

    private lateinit var list: SingleLinkedList

    @BeforeEach
    fun setUp() {
        list = SingleLinkedList()
    }

    @Test
    fun `add elements`() {
        list.add(1)
        list.add(2)
        list.add(3)

        assertEquals(3, list.size)
        for (i in list.withIndex()) {
            assertEquals(i.index + 1, i.value)
        }
    }

    @Test
    fun `addFirst works correctly`() {
        list.add(2)
        list.addFirst(1)

        assertEquals(1, list[0])
        assertEquals(2, list[1])
    }

    @Test
    fun `remove element`() {
        list.add(1)
        list.add(2)
        list.add(3)

        assertTrue(list.remove(2))
        assertEquals(2, list.size)
        assertEquals(1, list[0])
        assertEquals(3, list[1])
    }

    @Test
    fun `remove non-existing element`() {
        list.add(1)
        list.add(2)

        val removed = list.remove(3)
        assertFalse(removed)
        assertEquals(2, list.size)
    }

    @Test
    fun `contains works`() {
        list.add(1)
        list.add(2)

        assertTrue(list.contains(1))
        assertFalse(list.contains(3))
    }

    @Test
    fun `get by index`() {
        list.add(10)
        list.add(20)
        list.add(30)

        assertEquals(10, list[0])
        assertEquals(20, list[1])
        assertEquals(30, list[2])
    }

    @Test
    fun `get throws exception on invalid index`() {
        list.add(10)
        list[0]

        assertThrows(IndexOutOfBoundsException::class.java) {
            list[5]
        }
    }

    @Test
    fun `indexOf works`() {
        list.add(10)
        list.add(20)
        list.add(30)

        assertEquals(1, list.indexOf(20))
        assertEquals(-1, list.indexOf(100))
    }

    @Test
    fun `set works`() {
        list.add(10)
        list.add(20)
        list.add(30)

        list[1] = 100
        assertEquals(100, list[1])
        assertEquals(30, list[2])
    }

    @Test
    fun `operations on empty list`() {
        assertEquals(0, list.size)
        assertFalse(list.remove(10))
        assertEquals(-1, list.indexOf(5))

        list.addFirst(100)
        assertEquals(1, list.size)
        assertEquals(100, list[0])

        list.remove(100)
        assertEquals(0, list.size)

        list.add(200)
        assertEquals(1, list.size)
        assertEquals(200, list[0])
    }

    @Test
    fun `remove first and last elements`() {
        list.add(10)
        list.add(20)
        list.add(30)
        list.add(40)

        assertTrue(list.remove(10))
        assertEquals(3, list.size)
        assertEquals(20, list[0])
        assertEquals(40, list[2])

        assertTrue(list.remove(40))
        assertEquals(2, list.size)
        assertEquals(20, list[0])
        assertEquals(30, list[1])

        assertTrue(list.remove(20))
        assertTrue(list.remove(30))
        assertEquals(0, list.size)

        assertThrows(IndexOutOfBoundsException::class.java) {
            list[0]
        }
    }

    @Test
    fun `iterator works correctly`() {
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)
        list.add(5)

        val iterator = list.iterator()
        var count = 0
        while (iterator.hasNext()) {
            val value = iterator.next()
            assertTrue(value in 1..5)
            count++
        }
        assertEquals(5, count)

        val iter = list.iterator()
        repeat(5) { iter.next() }
        assertFalse(iter.hasNext())
        assertThrows(NoSuchElementException::class.java) {
            iter.next()
        }

        val collected = mutableListOf<Int>()
        for (item in list) {
            collected.add(item)
        }
        assertEquals(listOf(1, 2, 3, 4, 5), collected)
    }
}