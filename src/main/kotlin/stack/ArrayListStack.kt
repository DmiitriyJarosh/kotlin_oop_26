package org.example.stack

class ArrayListStack : Stack {
    var data = CustomArrayList()
    // Add item to stack
    fun push(value: Int)

    // Get and remove top item from stack
    fun pop(): Int

    // Get top item from stack
    fun peek(): Int

    val isEmpty: Boolean
}