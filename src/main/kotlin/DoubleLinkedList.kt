package org.example

class DoubleLinkedList : CustomList {

        private var head: Node? = null
        private var tail: Node? = null
        private var _size: Int = 0

        private class Node(
            var value: Int,
            var next: Node? = null,
            var prev: Node? = null
        )

    override val size: Int
        get() = _size

    override fun add(element: Int) {
        val newNode = Node(element)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            newNode.prev = tail
            tail?.next = newNode
            tail = newNode
        }
        _size++
    }

    override operator fun set(index: Int, value: Int) {
        checkIndex(index)
        getNode(index).value = value
    }

    override fun addFirst(element: Int) {
        val newNode = Node(element, next = head)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            head?.prev = newNode
            head = newNode
        }
        _size++
    }

    override operator fun get(index: Int): Int {
        checkIndex(index)
        return getNode(index).value
    }

    private fun getNode(index: Int): Node {
        if (index < _size / 2) {
            var current = head
            repeat(index) { current = current?.next }
            return current!!
        } else {
            var current = tail
            repeat(_size - 1 - index) { current = current?.prev }
            return current!!
        }
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
        var current = head
        while (current != null) {
            if (current.value == element) {
                val before = current.prev
                val after = current.next

                if (before == null) head = after else before.next = after
                if (after == null) tail = before else after.prev = before

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
            override fun hasNext(): Boolean {
                if (current != null) return true
                return false
            }

            override fun next(): Int {
                val res = current?.value ?: throw NoSuchElementException()
                current = current?.next
                return res
            }
        }
    }

    companion object {
        fun doubleLinkedListOf(vararg items: Int) =
            items.fold(DoubleLinkedList()) { list, item ->
                list.also{ it.add(item) }
            }
    }
}