package org.example

interface CustomList : Iterable<Int> {
    /*
     * Get element in the list at the given index
     * Throw IndexOutOfBoundsException to the wrong index given.
     */
    operator fun get(index: Int): Int
    /*
     * Set element in the list at the given index
     * Throw IndexOutOfBoundsException to the wrong index given.
     */
    operator fun set(index: Int, value: Int)
    /*
     * Add element to the list at the end
     */
    fun add(element: Int)
    /*
     * Add element to the list at the beginning
     */
    fun addFirst(element: Int)
    /* Remove element from the list.
     * Return true if the value was deleted.
     * Return false if the value was not deleted.
     */
    fun remove(element: Int): Boolean
    /*
     * Return the index of the element in the list.
     * Throw NoSuchElementException if the element was not found.
     */
    fun indexOf(element: Int): Int
    val size: Int
}