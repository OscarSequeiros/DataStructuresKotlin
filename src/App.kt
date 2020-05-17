import linkedlist.*

fun main() {
    val node1 = Node("1")
    val node2 = Node("2")
    val node3 = Node("3")

    node2.next = node3
    node1.next = node2

    val list = LinkedList<Int>()
    list.add(1)
    list.add(3)
    list.add(5)
    list.add(7)
    list.add(9)
    list.add(11)

    val anotherList = LinkedList<Int>()
    anotherList.add(2)
    anotherList.add(4)
    anotherList.add(6)

    println(list.mergeSorted(anotherList))
}