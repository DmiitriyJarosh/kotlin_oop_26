package org.example

class SingleLinkedList : CustomList {

    class Node(var value: Int, var next: Node? = null)

    private var root: Node? = null
    private var last: Node? = null
    override var size: Int = 0

    override fun add(element: Int) {
        val node = Node(element)
        if (root == null) {
            root = node
            last = node
        } else {
            last?.next = node
            last = node
        }
        size++
    }

    override operator fun set(index: Int, value: Int) {
        if ((index < 0) || (index >= size)) {
            throw IndexOutOfBoundsException()
        }
        if (index == size - 1) {
            last?.value = value
            return
        }
        var current = root
        for (i in 0 until index) {
            current = current?.next
        }
        current?.value = value
    }

    override fun addFirst(element: Int) {
        val node = Node(element, root)
        if (size == 0) {
            last = node
        }
        root = node
        size++
    }

    override operator fun get(index: Int): Int {
        if ((index < 0) || (index >= size)) {
            throw IndexOutOfBoundsException()
        }
        if (index == size - 1) {
            return last?.value ?: throw IllegalStateException()
        }
        var current = root
        for (i in 0 until index) {
            current = current?.next
        }
        return current?.value ?: throw IllegalStateException()
    }

    override fun indexOf(element: Int): Int {
        var current = root
        var ind = 0
        while (current != null) {
            if (current.value == element) {
                return ind
            }
            ind++
            current = current.next
        }
        return -1
    }

    override fun remove(element: Int): Boolean {
        if (root == null) {
            return false
        }
        if (root?.value == element) {
            root = root?.next
            if (root == null) last = null
            size--
            return true
        }
        var current = root
        while (current?.next != null) {
            if (current.next?.value == element) {
                if (current.next == last) {
                    last = current
                }
                current.next = current.next?.next
                size--
                return true
            }
            current = current.next
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            var current = root
            override fun hasNext(): Boolean = current != null
            override fun next(): Int {
                if (!hasNext()) throw NoSuchElementException()
                val value = current!!.value
                current = current?.next
                return value
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