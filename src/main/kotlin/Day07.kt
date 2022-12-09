fun main() {
    val lines = getLines("day07/input2.txt")

    var i = 0

    val root = TreeNode("/", 0, mutableListOf(), true, null)
    var currentDir = root

    while (i < lines.size) {
        val line = lines[i]
        if (line.startsWith("$ cd")) {
            val path = line.substringAfter("$ cd ")
            currentDir = when (path) {
                "/" -> root
                ".." -> currentDir.parent ?: root
                else -> currentDir.children.first { it.name == path }
            }
            i++
        } else if (line.startsWith("$ ls")) {

            while (++i < lines.size && !lines[i].startsWith("$")) {
                if (lines[i].startsWith("dir")) {
                    currentDir.add(lines[i].substringAfter("dir "), true)
                } else {
                    val (size, name) = lines[i].split(" ")
                    currentDir.add(name, size = size.toLong())
                }
            }
        }
    }

    println(part1(root))

    println(part2(root, root.size, 30000000 - (70000000 - root.size)))

}

fun part2(dir: TreeNode, closest: Long, required: Long): Long {
    return dir.children.filter { it.isDir }.fold(getClosest(required, closest, dir.size)) { acc, it ->
        val close = part2(it, acc, required)
        getClosest(required, close, acc)
    }
}

private fun getClosest(min: Long, second: Long, first: Long) =
    if (first in min..second) first else second

fun part1(dir: TreeNode): Long {
    val current = if (dir.size < 100000) dir.size else 0

    return dir.children.filter { it.isDir }.fold(current) { acc, node ->
        acc + part1(node)
    }
}

data class TreeNode(val name: String, var size: Long, val children: MutableList<TreeNode>, val isDir: Boolean, val parent: TreeNode?){
    fun add(name: String, isDir: Boolean = false, size: Long = 0){
        children.add(TreeNode(name, size, mutableListOf(), isDir, this))
        recalculateSize(size)
    }

    private fun recalculateSize(size: Long){
        this.size += size
        parent?.recalculateSize(size)
    }
}

