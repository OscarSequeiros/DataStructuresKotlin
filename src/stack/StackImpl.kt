package stack

class StackImpl<T> : Stack<T> {

    private val storage = arrayListOf<T>()

    override fun toString(): String = buildString {
        appendln("-- top --")
        storage.asReversed().forEach() { appendln("$it") }
        append("---------")
    }

    override fun push(element: T) {
        storage.add(element)
    }

    override fun pop(): T? {
        return if (isEmpty)
            null
        else
            storage.removeAt(storage.size - 1)
    }

    override fun peek(): T? {
        return storage.lastOrNull()
    }

    override val count: Int
        get() = storage.size

    companion object {

        fun <Element: Any> create(items: Iterable<Element>): Stack<Element> {
            val stack = StackImpl<Element>()
            for (item in items) {
                stack.push(item)
            }
            return stack
        }
    }
}

fun <Element: Any> stackOf(vararg elements: Element): Stack<Element> {
    return StackImpl.create(elements.asList())
}