package org.example.stack

import org.example.list.CustomArrayList
import org.example.list.CustomList

const val DEFAULT_ARRAY_LIST_STACK_SIZE = 16

class ArrayListStack : Stack, CustomList by CustomArrayList(DEFAULT_ARRAY_LIST_STACK_SIZE) {
    // Add item to stack
    override fun push(value: Int) {
        add(value)
    }

    // Get and remove top item from stack
    override fun pop(): Int {
        val ret = peek()
        remove(peek())
        return ret
    }

    // Get top item from stack
    override fun peek(): Int {
        return get(size-1)
    }

    override val isEmpty: Boolean get() = size == 0
}