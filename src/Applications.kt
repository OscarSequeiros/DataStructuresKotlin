import stack.stackOf

fun String.checkParentheses(): Boolean {
    val stack = stackOf<Char>()
    for (char in this) {
        when (char) {
            '(' -> stack.push(char)
            ')' -> if (stack.isEmpty) return false else stack.pop()
        }
    }
    return stack.isEmpty
}