package linkedlist

data class Node<T>(
    var value: T,
    var next: Node<T>? = null
) {

    private val hasNext: Boolean
        get() = next != null

    override fun toString(): String {
        return if (hasNext) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}