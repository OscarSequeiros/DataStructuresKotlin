import doublylinkedlist.DoublyLinkedList
import queue.ArrayListQueue
import queue.LinkedListQueue

fun main() {

    val aString = "((()))"
    println(aString.checkParentheses())

    val myQueue = LinkedListQueue<String>().apply {
        enqueue("Tom")
        enqueue("Rick")
        enqueue("Harry")
    }
}