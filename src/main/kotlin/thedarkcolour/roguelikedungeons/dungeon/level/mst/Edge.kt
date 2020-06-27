package thedarkcolour.roguelikedungeons.dungeon.level.mst

class Edge<P>(val start: P, val end: P, val length: Double) : Comparable<Edge<P>> {
    /**
     * Compares this object with the specified object for order. Returns zero if this object is equal
     * to the specified [other] object, a negative number if it's less than [other], or a positive number
     * if it's greater than [other].
     */
    override fun compareTo(other: Edge<P>): Int {
        return length.compareTo(other.length)
    }
}