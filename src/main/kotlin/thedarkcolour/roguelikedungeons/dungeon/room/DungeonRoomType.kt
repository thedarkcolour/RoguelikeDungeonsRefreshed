package thedarkcolour.roguelikedungeons.dungeon.room

enum class DungeonRoomType(val get: () -> IDungeonRoom, val intersection: Boolean = false, val secret: Boolean = false) {
    BRICK(::BrickRoom),
    CREEPER(::CreeperRoom),
    CRYPT(::CryptRoom),
    //ENCHANT(),
    ENDER(),
    FIRE(),
    //MUSIC(),
    NETHER(),
    NETHER_FORT(),
    PIT(),
    PRISON(),
    SLIME(),
    //SMITH(),
    SPIDER(),
    CAKE(),
    //LAB(),
    CORNER(),
    //MESS(),
    //ETHO(),
    //ENIKO(),
    //B_TEAM(),
    OSSUARY(),
    OBSIDIAN(),
    //AVIDYA(),
    //STORAGE(),
    //ASHLEA(),
    //FIREWORK(),
    //BEDROOM(),
    //REWARD(),
    //LIBRARY(),
    //PYRAMID_TOMB(),
    DARK_HALL(),
    TREETHO(::TreethoRoom),
    LINKER(::DungeonLinker),
    LINKER_TOP(::DungeonLinkerTop),
    //PYRAMID_SPAWNER(),
    //PYRAMID_CORNER(),
    BLAZE();
}