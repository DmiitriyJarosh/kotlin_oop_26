package org.example

class SingleLinkedList : CustomList {

    class Node {
        var value:Int = 0
        var next:Node? = null
    }

    var head:Node? = null

    private val inner = mutableListOf<Int>()

    override val size: Int
        get() {
            head?: return 0

            var curr = head
            var len:Int = 0
            while (curr != null) {
                lenn++
                curr = curr.next
            }
            return len
        }

    override fun add(element: Int) {
        if (head == null) {
            head = Node().also {it.value = element}
        }
        else {
            var curr:Node? = head
            while (curr?.next != null)
                curr = curr.next
            curr?.next = Node().also{it.value = element}
        }
    }

    override operator fun set(index: Int, value: Int) {
        if ((index >= size) || (index < 0))
            throw IndexOutOfBoundsException()

        var i:Int = 0
        var curr = head
        while (i != index && (curr?.next != null)) {
            curr = curr.next
            i++
        }
        curr?.value = value
    }


    override fun addFirst(element: Int) {
        head = Node().also {
            it.value = element
            it.next = head
        }
    }

    override operator fun get(index: Int): Int {
        if ((index < 0) || (index >= size)) throw IndexOutOfBoundsException()

        var curr:Node? = head
        var i = 0

        while (i != index) {
            curr = curr.next
            i++
        }

        return curr?.value
    }

    override fun indexOf(element: Int): Int {
        var index = 0

        for (i in this)
        {
            if (i == element)
                return index
            index++
        }

        return -1
    }

    override fun remove(element: Int) {
        var current = head
        var previous: Node? = null

        while (current != null) {
            if (current.value == element) {
                if (previous == null) {
                    head = current.next
                } else {
                    previous.next = current.next
                }
                return true
            }
            previous = current
            current = current.next
        }
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            var cur:Node? = head
            override fun hasNext(): Boolean {
                return cur != null
            }

            override fun next(): Int {
                val result:Int? = cur?.value
                cur = cur?.next
                return result ?: throw IllegalStateException()
            }
        }
    }


    companion object {
        fun singleLinkedListOf(vararg items: Int) =
            items.fold(SingleLinkedList()) { list, item ->
                list.also{ it.add(item) }
            }
    }
}
