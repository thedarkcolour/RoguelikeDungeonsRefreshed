package thedarkcolour.roguelikedungeons.dungeon.level.mst

class Graph<P> {
    val points = ArrayList<P>()
    val edges = ArrayList<Edge<P>>()

    fun addEdge(edge: Edge<P>) {
        val start = edge.start
        val end = edge.end

        if (!points.contains(start)) points.add(start)
        if (!points.contains(end)) points.add(end)

        edges.add(edge)
    }
}