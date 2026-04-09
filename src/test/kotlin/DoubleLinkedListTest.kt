import org.example.DoubleLinkedList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DoubleLinkedListTest {
    private lateinit var list: DoubleLinkedList

    @BeforeEach
    fun setUp() {
        list = DoubleLinkedList()
    }

    @Test
    fun `add elements`() {
        list.add(1)
        list.add(2)
        list.add(3)

        assertEquals(3, list.size)
        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(3, list[2])
    }

    @Test
    fun `addFirst works correctly`() {
        list.add(2)
        list.addFirst(1)

        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(2, list.size)
    }

    @Test
    fun `remove middle element`() {
        list.add(1)
        list.add(2)
        list.add(3)

        assertTrue(list.remove(2))
        assertEquals(2, list.size)
        assertEquals(1, list[0])
        assertEquals(3, list[1])
    }

    @Test
    fun `remove head and tail`() {
        list.add(1)
        list.add(2)
        list.add(3)

        assertTrue(list.remove(1))
        assertEquals(2, list[0])

        assertTrue(list.remove(3))
        assertEquals(1, list.size)
        assertEquals(2, list[0])
    }

    @Test
    fun `remove non-existing element`() {
        list.add(1)
        val removed = list.remove(5)
        assertFalse(removed)
        assertEquals(1, list.size)
    }

    @Test
    fun `get and set works`() {
        list.add(10)
        list.add(20)

        assertEquals(10, list[0])
        list[0] = 100
        assertEquals(100, list[0])
    }

    @Test
    fun `indexOf works`() {
        list.add(10)
        list.add(20)
        list.add(30)

        assertEquals(0, list.indexOf(10))
        assertEquals(1, list.indexOf(20))
        assertEquals(2, list.indexOf(30))
        assertEquals(-1, list.indexOf(50))
    }

    @Test
    fun `throws exception on invalid index`() {
        list.add(10)
        assertThrows(IndexOutOfBoundsException::class.java) {
            list[10]
        }
        assertThrows(IndexOutOfBoundsException::class.java) {
            list[-1]
        }
    }

    @Test
    fun `iterator works`() {
        list.add(1)
        list.add(2)
        list.add(3)

        val result = mutableListOf<Int>()
        for (item in list) {
            result.add(item)
        }
        assertEquals(listOf(1, 2, 3), result)
    }
}
