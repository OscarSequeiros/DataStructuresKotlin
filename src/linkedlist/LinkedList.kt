package linkedlist

import stack.StackImpl

class LinkedList<T> : Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    override var size = 0
        private set

    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator(this)
    }

    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element)
                return true
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements) {
            if (!contains(searched)) return false
        }
        return true
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements)
            append(element)
        return true
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item == element) {
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (element in elements) {
            result = remove(element) || result
        }
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()

            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }

    override fun isEmpty() = size == 0

    override fun toString(): String {
        return if (isEmpty()) {
            "Empty list"
        } else {
            head.toString()
        }
    }

    fun push(element: T): LinkedList<T> {
        head = Node(value = element, next = head)
        if (tail == null) {
            tail = head
        }
        size++
        return this
    }

    fun append(element: T): LinkedList<T> {
        return if (isEmpty()) {
            return push(element)
        } else {
            tail?.next = Node(element)
            tail = tail?.next
            size++
            this
        }
    }

    fun nodeAt(index: Int): Node<T>? {
        var currentNode = head
        var currentIndex = 0

        while (currentIndex < index && currentNode != null) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(element: T, afterNode: Node<T>): Node<T> {
        if (tail == afterNode) {
            append(element)
            return tail!!
        }
        val newNode = Node(value = element, next = afterNode.next)
        afterNode.next = newNode
        size++
        return newNode
    }

    fun pop(): T? {
        if (!isEmpty()) {
            size--
        }
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }

        return result
    }

    fun removeLast(): T? {
        val head = head ?: return null

        if (head.next == null) return pop()

        size--

        var prev = head
        var current = head
        var next = current.next
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }

        prev.next = null
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value

        if (node.next == tail) {
            tail = node
        }

        if (node.next != null) {
            size--
        }

        node.next = node.next?.next
        return result
    }

    fun reverse(): LinkedList<T> {
        var current = head
        var previous: Node<T>? = null
        while (current != null) {
            val next = current.next
            current.next = previous
            previous = current
            if (previous.next == null) {
                tail = previous
            }
            head = current
            current = next
        }
        return this
    }
}

fun <T> LinkedList<T>.printInReverse() {
    nodeAt(0)?.printInReverse()
}

fun <T> Node<T>.printInReverse() {
    next?.printInReverse()

    if (this.next != null) {
        print(" -> ")
    }
    print(this.value.toString())
}

fun <T> LinkedList<T>.getMiddle(): Node<T>? {
    var slow = nodeAt(0)
    var fast = nodeAt(0)
    while (fast != null) {
        fast = fast.next
        if (fast != null) {
            fast = fast.next
            slow = slow?.next
        }
    }
    return slow
}

fun <T> addInReverse(list: LinkedList<T>, node: Node<T>) {
    val next = node.next
    if (next != null) {
        addInReverse(list, next)
    }
    list.append(node.value)
}

fun <T> LinkedList<T>.reversed(): LinkedList<T> {
    val result = LinkedList<T>()
    val head = this.nodeAt(0)
    if (head != null) {
        addInReverse(result, head)
    }
    return result
}

fun <T : Comparable<T>> LinkedList<T>.mergeSorted(otherList: LinkedList<T>): LinkedList<T> {
    if (this.isEmpty()) return otherList
    if (otherList.isEmpty()) return this

    val result = LinkedList<T>()

    var left = nodeAt(0)
    var right = otherList.nodeAt(0)

    while (left != null && right != null) {
        if (left.value < right.value) {
            left = append(result, left)
        } else {
            right = append(result, right)
        }
    }

    while (left != null) {
        left = append(result, left)
    }
    while (right != null) {
        right = append(result, right)
    }

    return result
}

private fun <T : Comparable<T>> append(result: LinkedList<T>, node: Node<T>): Node<T>? {
    result.append(node.value)
    return node.next
}

fun <T> LinkedList<T>.printInReverseWithStacks() {
    val stack = StackImpl<T>()
    for (node in this) {
        stack.push(node)
    }
    var node = stack.pop()
    while (node != null) {
        println(node)
        node = stack.pop()
    }
}