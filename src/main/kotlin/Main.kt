import org.example.DoubleLinkedList.Companion.doubleLinkedListOf

fun main() {
    val t = doubleLinkedListOf(1, 2, 3)
    t[2] = 5
    print(t[2])
}