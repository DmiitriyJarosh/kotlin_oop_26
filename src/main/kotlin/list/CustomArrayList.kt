package org.example.list
import kotlin.math.min

class CustomArrayList(size: Int) : CustomList{
    var inner = IntArray(size)
    var curSize = 0

    private fun swap(i: Int, j: Int) {
        val temp = inner[i]
        inner[i] = inner[j]
        inner[j] = temp
    }
    private fun removeIdx(i: Int) {
        if (i >= size || i < 0)
            throw IndexOutOfBoundsException("Index: $i, Size: $size")
        for (j in size - 1 downTo i + 1) {
            swap(j, j - 1)
        }
        curSize--
    }
    override  fun get(index: Int): Int {
        if (index < size && index >= 0)
            return inner[index]
        throw IndexOutOfBoundsException("Index: $index, Size: $size")
    }
    override fun set(index: Int, value: Int) {
        if (index < size && index >= 0)
            inner[index] = value
        else
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
    }
    override fun add(element: Int) {
        if (size + 1 >= inner.size) {
            growSize()
        }
        inner[curSize++] = element
    }
    override fun addFirst(element: Int) {
        if (size + 1 >= inner.size) {
            growSize()
        }
        for (i in size downTo 1) {
            swap(i-1, i)
        }
        inner[0] = element
        curSize++
    }
    override fun remove(element: Int): Boolean {
        for (i in 0 until size) {
            if (inner[i] == element) {
                removeIdx(i)
                return true
            }
        }
        return false
    }
    override fun indexOf(element: Int): Int {
        for (i in 0 until size) {
            if (inner[i] == element) {
                return i
            }
        }
        return -1
    }
    override val size: Int get() = curSize


    private fun growSize() {
        val newData = IntArray(inner.size * 2)

        for (i in 0 until inner.size ) {
            newData[i] = inner[i]
        }

        inner = newData
    }
    private fun resize(newSize: Int): CustomArrayList {
        val newList = CustomArrayList(newSize)
        val copySize = min(newSize, size)
        for (i in 0 until copySize) {
            newList[i] = inner[i]
        }
        return newList
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            var index = 0
            override fun hasNext(): Boolean {
                return index < size
            }

            override fun next(): Int {
                return inner[index++]
            }
        }
    }

    companion object {
        fun customArrayListOf(vararg items: Int) =
            items.fold(CustomArrayList(items.size)) { list, item ->
                list.also { it.add(item) }
            }
    }
}