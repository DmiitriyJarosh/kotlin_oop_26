package org.example.list

open class SingleLinkedList : CustomList {

    class Node (var value: Int, var next: Node? = null, var prev: Node? = null) {}

    var root: Node? = null
    var node_insert: Node? = null

    override val size: Int
        get() {
            var sz = 0
            var cur = root
            while (cur != null) {
                cur = cur.next
                sz++
            }
            return sz
        }

    override fun add(element: Int) {
        val newNode = Node(element, null, node_insert)
        node_insert?.let{
            it.next = newNode
        } ?: run {
            root = newNode
        }
        node_insert = newNode
    }

    override operator fun set(index: Int, value: Int) {
        var cur = root
        for (i in 0 until index) {
            cur = cur?.next ?: throw IndexOutOfBoundsException()
        }
        cur?.value = value ?: throw IndexOutOfBoundsException()
    }

    override fun addFirst(element: Int) {
        root = Node(element, root, null)
    }

    override operator fun get(index: Int): Int {
        var cur = root
        for (i in 0 until index) {
            cur = cur?.next ?: throw IndexOutOfBoundsException()
        }
        return cur?.value ?: throw IndexOutOfBoundsException()
    }

    override fun indexOf(element: Int): Int {
        var cur = root
        var idx = 0
        while (cur != null) {
            if (cur.value == element) {
                return idx
            }
            idx++
            cur = cur.next
        }
        return -1
    }

    override fun remove(element: Int): Boolean {
        var cur: Node = root ?: return false

        if (cur.value == element) {
            root = cur.next
            return true
        }

        while (true) {
            if (cur.next == null)
                    return false
            if (cur.next?.value == element)
                break
            cur = cur.next ?: return false
        }

        if (cur.next == null) {
            return false
        } else {
            cur.next = cur.next?.next
            cur?.next?.prev = cur
        }
        return true
    }

    fun removeLast() {
        if (node_insert == null)
            throw NoSuchElementException("List is empty.")
        if (node_insert == root) {
            root = null
        }

        node_insert = node_insert?.prev
        node_insert?.next = null
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {

            var cur = root

            override fun hasNext(): Boolean {
                return cur != null
            }

            override fun next(): Int {
                var ret = cur?.value ?: throw NoSuchElementException("No such element in the list.")

                cur = cur?.next
                return ret
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