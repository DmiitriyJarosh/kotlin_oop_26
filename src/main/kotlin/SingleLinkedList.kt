package org.example

import java.util.concurrent.ExecutionException

class SingleLinkedList : CustomList {
    override var size: Int = 0
    var beginNode : Node? = null

    class Node(element: Int){
        var value: Int = element
        var next: Node? = null
    }

    override fun add(element: Int) {
        val newNode = Node(element)
        if (size == 0){
            beginNode = newNode
            size++
            return
        }
        var cur: Node? = beginNode
        var i = 0
        while (cur?.next != null){
            cur = cur.next
            i++
        }
        cur?.next = newNode
        size++
    }

    override operator fun set(index: Int, value: Int) {
        var cur: Node? = beginNode
        var ind = 0
        while (ind != index) {
            cur = cur?.next ?: return
            ind++
        }
        cur?.value = value
    }
    override fun addFirst(element: Int) {
        val newNode: Node = Node(element)
        newNode.next = beginNode
        beginNode = newNode
        size++
    }

    override operator fun get(index: Int): Int {
        var cur: Node? = beginNode
        var ind = 0
        while(cur != null){
            if(ind == index){
                return cur.value
            }
            cur = cur.next
            ind++
        }
        throw IndexOutOfBoundsException()
        }

    override fun indexOf(element: Int): Int {
        var ind = 0
        var cur: Node? = beginNode ?: return -1

        do{
            if (cur?.value == element){
                return ind
            }
            cur = cur?.next ?: return -1
            ind++
        }while (ind < size)
        return -1
    }


    override fun remove(element: Int): Boolean {
        var cur: Node? = beginNode ?: return false
        if (cur?.value == element){
            beginNode = beginNode?.next
            size--
            return true
        }
        var curNext: Node? = cur?.next ?: return false
        while (curNext != null){
            if (curNext.value == element){
                cur?.next = curNext.next
                size--
                return true
            }
            cur = curNext
            curNext = curNext.next
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            var cur = beginNode
            override fun hasNext(): Boolean {
                return cur != null
            }
            override fun next(): Int {
                val element = cur!!.value
                cur = cur?.next
                return element
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