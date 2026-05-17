package org.example

class SingleLinkedList : CustomList {

    // Узел списка
    private class Node(
        var value: Int,
        var next: Node? = null
    )

    // Первый элемент списка
    private var head: Node? = null

    // Размер списка
    private var currentSize = 0

    override val size: Int
        get() = currentSize

    // Добавление в конец
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

        currentSize++
    }

    // Изменение элемента по индексу
    override operator fun set(index: Int, value: Int) {
        val node = getNode(index)
        node.value = value
    }

    // Добавление в начало
    override fun addFirst(element: Int) {
        head = Node(element, head)
        currentSize++
    }

    // Получение элемента по индексу
    override operator fun get(index: Int): Int {
        return getNode(index).value
    }

    // Поиск индекса элемента
    override fun indexOf(element: Int): Int {
        var current = head
        var index = 0

        while (current != null) {
            if (current.value == element) {
                return index
            }

            current = current.next
            index++
        }

        return -1
    }

    // Удаление элемента
    override fun remove(element: Int): Boolean {

        // Пустой список
        if (head == null) {
            return false
        }

        // Если удаляем первый элемент
        if (head?.value == element) {
            head = head?.next
            currentSize--
            return true
        }

        var current = head

        while (current?.next != null) {

            if (current.next?.value == element) {
                current.next = current.next?.next
                currentSize--
                return true
            }

            current = current.next
        }

        return false
    }

    // Получение узла по индексу
    private fun getNode(index: Int): Node {

        if (index < 0 || index >= currentSize) {
            throw IndexOutOfBoundsException("Index: $index")
        }

        var current = head
        var currentIndex = 0

        while (currentIndex < index) {
            current = current?.next
            currentIndex++
        }

        return current!!
    }

    // Итератор
    override fun iterator(): Iterator<Int> {

        return object : Iterator<Int> {

            private var current = head

            override fun hasNext(): Boolean {
                return current != null
            }

            override fun next(): Int {

                val value = current?.value
                    ?: throw NoSuchElementException()

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