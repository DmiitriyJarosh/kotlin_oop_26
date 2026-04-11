package org.example

class SingleLinkedList : CustomList {
    private var head: Node? = null
    private var _size: Int = 0

    private class Node(
        var value: Int,
        var next: Node? = null,
    )

    override val size: Int
        get() = _size

    override fun add(element: Int) {
        val newNode = Node(element)
        if (head == null) {
            head = newNode
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newNode
        }
        _size++
    }

    override operator fun set(
        index: Int,
        value: Int,
    ) {
        checkIndex(index)
        getNode(index).value = value
    }

    override fun addFirst(element: Int) {
        val newNode = Node(element, next = head)
        head = newNode
        _size++
    }

    override operator fun get(index: Int): Int {
        checkIndex(index)
        return getNode(index).value
    }

    private fun getNode(index: Int): Node {
        var current = head
        repeat(index) { current = current?.next }
        return current!!
    }

    private fun checkIndex(index: Int) {
        if (index !in 0 until _size) throw IndexOutOfBoundsException()
    }

    override fun indexOf(element: Int): Int {
        var current = head
        var index = 0
        while (current != null) {
            if (current.value == element) return index
            current = current.next
            index++
        }
        return -1
    }

    override fun remove(element: Int): Boolean {
        if (head == null) return false

        if (head?.value == element) {
            head = head?.next
            _size--
            return true
        }

        var current = head
        while (current?.next != null) {
            if (current.next?.value == element) {
                current.next = current.next?.next
                _size--
                return true
            }
            current = current.next
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var current = head

            override fun hasNext(): Boolean = current != null

            override fun next(): Int {
                val res = current?.value ?: throw NoSuchElementException()
                current = current?.next
                return res
            }
        }
    }

    companion object {
        fun singleLinkedListOf(vararg items: Int) =
            items.fold(SingleLinkedList()) { list, item ->
                list.also { it.add(item) }
            }
    }
}
