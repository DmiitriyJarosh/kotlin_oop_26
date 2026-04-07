package org.example.stack

import org.example.list.SingleLinkedList

class SingleLinkedStack : Stack, SingleLinkedList() {
    override fun push(value: Int) {
        add(value)
    }

    override fun pop(): Int {
        val ret = peek()
        remove(ret)
        return ret
    }

    override fun peek(): Int {
        return node_insert?.value ?: throw IndexOutOfBoundsException("Stack is empty.")
    }

    override val isEmpty: Boolean
        get() = size == 0
}