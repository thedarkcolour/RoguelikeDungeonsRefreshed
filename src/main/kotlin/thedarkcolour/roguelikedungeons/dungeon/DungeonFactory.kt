package thedarkcolour.roguelikedungeons.dungeon

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import thedarkcolour.roguelikedungeons.Complete
import thedarkcolour.roguelikedungeons.dungeon.room.DungeonRoomType
import thedarkcolour.roguelikedungeons.dungeon.room.IDungeonRoom
import thedarkcolour.roguelikedungeons.loot.weight.WeightedEntry
import thedarkcolour.roguelikedungeons.loot.weight.WeightedPool
import thedarkcolour.roguelikedungeons.util.nextEnum
import java.util.*

@Complete
@Suppress("ReplacePutWithAssignment")
class DungeonFactory(private var base: DungeonRoomType) {
    private val singles = Object2IntOpenHashMap<DungeonRoomType>()
    private val multiples = Object2IntOpenHashMap<DungeonRoomType>()
    private val singleRooms by lazy(::RoomIterator)

    constructor(copy: DungeonFactory) : this(copy.base) {
        for (room in copy.singles.keys) {
            singles[room] = copy.singles.getInt(copy)
        }

        for (room in copy.multiples.keys) {
            multiples[room] = copy.multiples.getInt(copy)
        }
    }

    constructor(base: DungeonFactory, other: DungeonFactory) : this(base.base) {
        if (other.singles.keys.isEmpty()) {
            for (room in base.singles.keys) {
                singles.put(room, base.singles.getInt(room))
            }
        } else {
            for (room in other.singles.keys) {
                singles.put(room, base.singles.getInt(room))
            }
        }

        if (other.multiples.keys.isEmpty()) {
            for (room in base.multiples.keys) {
                multiples.put(room, base.multiples.getInt(room))
            }
        } else {
            for (room in other.multiples.keys) {
                multiples.put(room, other.multiples.getInt(room))
            }
        }
    }

    fun getRoom(rand: Random): IDungeonRoom {
        if (singleRooms.hasNext()) return singleRooms.next()

        val keys = multiples.keys
        if (keys.isEmpty()) return base.get()

        val weighted = WeightedPool<DungeonRoomType>()

        for (room in keys) {
            weighted.add(WeightedEntry(room, multiples.getInt(room)))
        }

        return weighted.get(rand)!!.get()
    }

    fun addSingle(room: DungeonRoomType, weight: Int = 1) {
        if (!singles.containsKey(room)) {
            singles.put(room, weight)
            return
        }

        val w = singles.getInt(room) + weight
        singles.put(room, w)
    }

    fun addRandom(type: DungeonRoomType, weight: Int) {
        multiples.put(type, weight)
    }

    /**
     * Indicates whether some other object is "equal to" this one. Implementations must fulfil the following
     * requirements:
     *
     * * Reflexive: for any non-null value `x`, `x.equals(x)` should return true.
     * * Symmetric: for any non-null values `x` and `y`, `x.equals(y)` should return true if and only if `y.equals(x)` returns true.
     * * Transitive:  for any non-null values `x`, `y`, and `z`, if `x.equals(y)` returns true and `y.equals(z)` returns true, then `x.equals(z)` should return true.
     * * Consistent:  for any non-null values `x` and `y`, multiple invocations of `x.equals(y)` consistently return true or consistently return false, provided no information used in `equals` comparisons on the objects is modified.
     * * Never equal to null: for any non-null value `x`, `x.equals(null)` should return false.
     *
     * Read more about [equality](https://kotlinlang.org/docs/reference/equality.html) in Kotlin.
     */
    override fun equals(other: Any?): Boolean {
        return when {
            other === null -> false
            other === this -> true
            other !is DungeonFactory -> false
            base != other.base -> false
            singles != other.singles -> false
            else -> multiples == other.multiples
        }
    }

    /**
     * Any class that implements [equals] should also implement [hashCode].
     */
    override fun hashCode(): Int {
        var result = base.hashCode()
        result = 31 * result + singles.hashCode()
        result = 31 * result + multiples.hashCode()
        return result
    }

    private inner class RoomIterator : Iterator<IDungeonRoom> {
        private val rooms = PriorityQueue<IDungeonRoom>()

        init {
            for (type in this@DungeonFactory.singles.keys) {
                for (i in 0 until this@DungeonFactory.singles.getInt(type)) {
                    rooms.add(type.get())
                }
            }
        }

        /**
         * Returns `true` if the iteration has more elements.
         */
        override fun hasNext(): Boolean {
            return !rooms.isEmpty()
        }

        /**
         * Returns the next element in the iteration.
         */
        override fun next(): IDungeonRoom {
            return rooms.poll()
        }
    }

    companion object {
        fun getRandom(rand: Random, numRooms: Int): DungeonFactory {
            val rooms = DungeonFactory(DungeonRoomType.CORNER)

            for (i in 0..numRooms) {
                val type = rand.nextEnum<DungeonRoomType>()

                if (rand.nextBoolean()) {
                    rooms.addRandom(type, 1)
                } else {
                    rooms.addSingle(type, 1)
                }
            }

            return rooms
        }
    }
}