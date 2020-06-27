package thedarkcolour.roguelikedungeons.dungeon.task

object DungeonTaskRegistry {
    private val tasks = hashMapOf(
        DungeonStage.LAYOUT to LayoutDungeonTask(),
        DungeonStage.ENCASE to EncaseDungeonTask(),
        DungeonStage.TUNNELS to TunnelsDungeonTask(),
        DungeonStage.ROOMS to RoomsDungeonTask(),
        DungeonStage.SEGMENTS to SegmentsDungeonTask(),
        DungeonStage.LINKS to LinksDungeonTask(),
        DungeonStage.TOWER to TowerDungeonTask(),
        DungeonStage.FILTERS to FiltersDungeonTask(),
        DungeonStage.LOOT to LootDungeonTask(),
    )

    fun getTask(stage: DungeonStage): IDungeonTask {
        return tasks[stage] ?: error("No matching task for dungeon stage")
    }
}