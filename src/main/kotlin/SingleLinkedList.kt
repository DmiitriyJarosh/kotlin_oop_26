package org.example

class SingleLinkedList : CustomList {

    private class Node (
        var value: Int,
        var next: Node? = null
    )

    private var head: Node? = null
    private var list_size: Int = 0

    override val size: Int
        get() = list_size

    override fun add(element: Int) {
        val node = Node(element, null)
        if (head == null) {
            head = node
        } else {
            var cur = head
            while (cur?.next != null) {
                cur = cur.next
            }
            cur?.next = node
        }
        list_size++
    }

    private fun getNode(index: Int): Node? {
        var cur = head
        var count: Int = 0
        while (count != index) {
            cur = cur?.next
            count++
        }
        return cur
    }

    override operator fun set(index: Int, value: Int) {
        val cur = getNode(index)
        cur?.value = value
    }

    override fun addFirst(element: Int) {
        val node = Node(element, null)
        node.next = head
        head = node
        list_size++
    }


    override operator fun get(index: Int): Int {
        val cur = getNode(index)
        return cur?.value ?: throw IndexOutOfBoundsException()
    }

    override fun indexOf(element: Int): Int {
        var cur: Node = head ?: return -1
        var index = 0
        while (cur.next != null) {
            if (cur.value == element) {
                return index
            }
            cur = cur.next!! //Гарантия того, что не null
            index++
        }
        return -1
    }


    override fun remove(element: Int): Boolean {
        var cur: Node? = head ?: return false

        if (head?.value == element) {
            head = head?.next
            list_size--
            return true
        }

        while (cur?.next != null) {
            if (cur.next?.value == element) {
                cur.next = cur.next?.next
                list_size--
                return true
            }
            cur = cur.next
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            var cur: Node? = head
            override fun hasNext(): Boolean {
                if (cur?.next != null) {
                    return true
                }
                return false
            }

            override fun next(): Int {
                val tmp = cur?.value ?: throw IllegalStateException("")
                cur = cur?.next
                return tmp
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