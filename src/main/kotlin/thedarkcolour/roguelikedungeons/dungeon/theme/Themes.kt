package thedarkcolour.roguelikedungeons.dungeon.theme

import net.minecraft.block.Blocks
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.state.properties.SlabType
import thedarkcolour.roguelikedungeons.config.Config
import thedarkcolour.roguelikedungeons.dungeon.*
import thedarkcolour.roguelikedungeons.dungeon.theme.palette.BlockPalette
import thedarkcolour.roguelikedungeons.dungeon.theme.palette.Door
import thedarkcolour.roguelikedungeons.dungeon.theme.placer.*
import thedarkcolour.roguelikedungeons.loot.weight.WeightedEntry

/** Block placers */
// todo move all block placers into a file called BlockPlacers.kt
// todo move all states into BlockStates.kt
// todo add remaining themes
private val IRON_BLOCK_PLACER = SimpleBlockPlacer(IRON_BLOCK)
private val GOLD_BLOCK_PLACER = SimpleBlockPlacer(GOLD_BLOCK)
private val DIAMOND_BLOCK_PLACER = SimpleBlockPlacer(DIAMOND_BLOCK)
private val EMERALD_BLOCK_PLACER = SimpleBlockPlacer(EMERALD_BLOCK)
private val LAPIS_BLOCK_PLACER = SimpleBlockPlacer(LAPIS_BLOCK)
private val STONE_BRICKS_PLACER = SimpleBlockPlacer(STONE_BRICKS)
private val SMOOTH_STONE_PLACER = SimpleBlockPlacer(Blocks.SMOOTH_STONE.defaultState)
private val BRICKS_PLACER = SimpleBlockPlacer(BRICKS)
private val CRACKED_STONE_BRICKS_PLACER = SimpleBlockPlacer(CRACKED_STONE_BRICKS)
private val MOSSY_STONE_BRICKS_PLACER = SimpleBlockPlacer(Blocks.MOSSY_STONE_BRICKS.defaultState)
private val COBBLESTONE_PLACER = SimpleBlockPlacer(COBBLESTONE)
val MOSSY_COBBLESTONE_PLACER = SimpleBlockPlacer(MOSSY_COBBLESTONE)
private val COAL_ORE_PLACER = SimpleBlockPlacer(Blocks.COAL_ORE.defaultState)
private val IRON_ORE_PLACER = SimpleBlockPlacer(Blocks.IRON_ORE.defaultState)
private val DIAMOND_ORE_PLACER = SimpleBlockPlacer(Blocks.DIAMOND_ORE.defaultState)
private val EMERALD_ORE_PLACER = SimpleBlockPlacer(Blocks.EMERALD_ORE.defaultState)
val GRAVEL_PLACER = SimpleBlockPlacer(GRAVEL)
val DIRT_PLACER = SimpleBlockPlacer(DIRT)
private val GLOWSTONE_PLACER = SimpleBlockPlacer(GLOWSTONE)
private val NETHER_BRICKS_PLACER = SimpleBlockPlacer(Blocks.NETHER_BRICKS.defaultState)
private val RED_NETHER_BRICKS_PLACER = SimpleBlockPlacer(Blocks.RED_NETHER_BRICKS.defaultState)
val NETHERRACK_PLACER = SimpleBlockPlacer(Blocks.NETHERRACK.defaultState)
val FIRE_PLACER = SimpleBlockPlacer(Blocks.FIRE.defaultState)
val IRON_BARS_PLACER = SimpleBlockPlacer(Blocks.IRON_BARS.defaultState)
val AIR_PLACER = SimpleBlockPlacer(Blocks.AIR.defaultState)
private val NETHER_QUARTZ_ORE_PLACER = SimpleBlockPlacer(Blocks.NETHER_QUARTZ_ORE.defaultState)
private val NETHER_WART_BLOCK_PLACER = SimpleBlockPlacer(Blocks.NETHER_WART_BLOCK.defaultState)
private val MAGMA_BLOCK_PLACER = SimpleBlockPlacer(Blocks.MAGMA_BLOCK.defaultState)
private val COAL_BLOCK_PLACER = SimpleBlockPlacer(Blocks.COAL_BLOCK.defaultState)
private val OAK_PLANKS_PLACER = SimpleBlockPlacer(Blocks.OAK_PLANKS.defaultState)
val FLIPPED_BIRCH_SLAB_PLACER = SimpleBlockPlacer(Blocks.BIRCH_SLAB.defaultState.with(BlockStateProperties.SLAB_TYPE, SlabType.TOP))
val SPRUCE_PLANKS_PLACER = SimpleBlockPlacer(SPRUCE_PLANKS)
private val VERTICAL_OAK_LOG_PLACER = SimpleBlockPlacer(Blocks.OAK_LOG.defaultState)
private val VERTICAL_SPRUCE_LOG_PLACER = SimpleBlockPlacer(VERTICAL_SPRUCE_LOG)
private val BLACK_CONCRETE_PLACER = SimpleBlockPlacer(Blocks.BLACK_CONCRETE.defaultState)
private val RED_CONCRETE_PLACER = SimpleBlockPlacer(Blocks.RED_CONCRETE.defaultState)
private val YELLOW_CONCRETE_PLACER = SimpleBlockPlacer(Blocks.YELLOW_CONCRETE.defaultState)
private val WHITE_CONCRETE_PLACER = SimpleBlockPlacer(Blocks.WHITE_CONCRETE.defaultState)
private val GREEN_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.GREEN_TERRACOTTA.defaultState)
private val BLACK_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.BLACK_TERRACOTTA.defaultState)
private val BROWN_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.BROWN_TERRACOTTA.defaultState)
private val YELLOW_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.YELLOW_TERRACOTTA.defaultState)
private val PURPLE_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.PURPLE_TERRACOTTA.defaultState)
private val LIGHT_BLUE_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.LIGHT_BLUE_TERRACOTTA.defaultState)
private val RED_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.RED_TERRACOTTA.defaultState)
private val BLUE_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.BLUE_TERRACOTTA.defaultState)
private val LIGHT_GRAY_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.LIGHT_GRAY_TERRACOTTA.defaultState)
private val WHITE_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.WHITE_TERRACOTTA.defaultState)
private val CYAN_TERRACOTTA_PLACER = SimpleBlockPlacer(Blocks.CYAN_TERRACOTTA.defaultState)
private val OBSIDIAN_PLACER = SimpleBlockPlacer(Blocks.OBSIDIAN.defaultState)
private val REDSTONE_BLOCK_PLACER = SimpleBlockPlacer(Blocks.REDSTONE_BLOCK.defaultState)
val PUMPKIN_STEM_PLACER = SimpleBlockPlacer(Blocks.PUMPKIN_STEM.defaultState)
val QUARTZ_BLOCK_PLACER = SimpleBlockPlacer(Blocks.QUARTZ_BLOCK.defaultState)
private val ANDESITE_PLACER = SimpleBlockPlacer(Blocks.ANDESITE.defaultState)
private val PACKED_ICE_PLACER = SimpleBlockPlacer(Blocks.PACKED_ICE.defaultState)
private val POLISHED_ANDESITE_PLACER = SimpleBlockPlacer(Blocks.POLISHED_ANDESITE.defaultState)
private val POLISHED_GRANITE_PLACER = SimpleBlockPlacer(Blocks.POLISHED_GRANITE.defaultState)
private val POLISHED_DIORITE_PLACER = SimpleBlockPlacer(Blocks.POLISHED_DIORITE.defaultState)
private val VERTICAL_DARK_OAK_LOG_PLACER = SimpleBlockPlacer(Blocks.DARK_OAK_LOG.defaultState)
private val VERTICAL_QUARTZ_PILLAR_PLACER = SimpleBlockPlacer(Blocks.QUARTZ_PILLAR.defaultState)
private val DARK_OAK_PLANKS_PLACER = SimpleBlockPlacer(Blocks.DARK_OAK_PLANKS.defaultState)
private val END_STONE_BRICKS_PLACER = SimpleBlockPlacer(Blocks.END_STONE_BRICKS.defaultState)
private val CHISELED_SANDSTONE_PLACER = SimpleBlockPlacer(Blocks.CHISELED_SANDSTONE.defaultState)
private val SANDSTONE_PLACER = SimpleBlockPlacer(Blocks.SANDSTONE.defaultState)
private val SMOOTH_SANDSTONE_PLACER = SimpleBlockPlacer(Blocks.SMOOTH_SANDSTONE.defaultState)
private val CUT_SANDSTONE_PLACER = SimpleBlockPlacer(Blocks.CUT_SANDSTONE.defaultState)
val SANDSTONE_SLAB_PLACER = SimpleBlockPlacer(Blocks.SANDSTONE_SLAB.defaultState)
private val GRASS_BLOCK_PLACER = SimpleBlockPlacer(Blocks.GRASS_BLOCK.defaultState)
private val INFESTED_COBBLESTONE_PLACER = SimpleBlockPlacer(Blocks.INFESTED_COBBLESTONE.defaultState)
private val INFESTED_STONE_BRICKS_PLACER = SimpleBlockPlacer(Blocks.INFESTED_STONE_BRICKS.defaultState)
private val INFESTED_MOSSY_STONE_BRICKS_PLACER = SimpleBlockPlacer(Blocks.INFESTED_MOSSY_STONE_BRICKS.defaultState)
private val INFESTED_CRACKED_STONE_BRICKS_PLACER = SimpleBlockPlacer(Blocks.INFESTED_CRACKED_STONE_BRICKS.defaultState)
val BIRCH_SAPLING_PLACER = SimpleBlockPlacer(Blocks.BIRCH_SAPLING.defaultState)
val YELLOW_STAINED_GLASS_PLACER = SimpleBlockPlacer(Blocks.YELLOW_STAINED_GLASS.defaultState)
val JACK_O_LANTERN_PLACER = SimpleBlockPlacer(Blocks.JACK_O_LANTERN.defaultState)
val CHEST_PLACER = SimpleBlockPlacer(Blocks.CHEST.defaultState)
val TRAPPED_CHEST_PLACER = SimpleBlockPlacer(Blocks.TRAPPED_CHEST.defaultState)
val TORCH_PLACER = SimpleBlockPlacer(Blocks.TORCH.defaultState)
val VINE_PLACER = SimpleBlockPlacer(Blocks.VINE.defaultState)
val TNT_PLACER = SimpleBlockPlacer(Blocks.TNT.defaultState)

val WATER_PLACER = SimpleBlockPlacer(WATER)
private val LAVA_PLACER = SimpleBlockPlacer(LAVA)

/** Stairs placers */
private val STONE_BRICK_STAIRS_PLACER = StairsBlockPlacer(STONE_BRICK_STAIRS)
private val COBBLESTONE_STAIRS_PLACER = StairsBlockPlacer(Blocks.COBBLESTONE_STAIRS.defaultState)
private val SANDSTONE_STAIRS_PLACER = StairsBlockPlacer(Blocks.SANDSTONE_STAIRS.defaultState)
private val BRICK_STAIRS_PLACER = StairsBlockPlacer(BRICK_STAIRS)
private val NETHER_BRICK_STAIRS_PLACER = StairsBlockPlacer(Blocks.NETHER_BRICK_STAIRS.defaultState)
private val QUARTZ_STAIRS_PLACER = StairsBlockPlacer(QUARTZ_STAIRS)
private val SPRUCE_STAIRS_PLACER = StairsBlockPlacer(SPRUCE_STAIRS)
private val OAK_STAIRS_PLACER = StairsBlockPlacer(Blocks.OAK_STAIRS.defaultState)
private val ACACIA_STAIRS_PLACER = StairsBlockPlacer(ACACIA_STAIRS)
private val DARK_OAK_STAIRS_PLACER = StairsBlockPlacer(Blocks.DARK_OAK_STAIRS.defaultState)

/** Jumble placers todo remove */
private val STONE_BRICK_JUMBLE = JumbleBlockPlacer(STONE_BRICKS_PLACER, CRACKED_STONE_BRICKS_PLACER, MOSSY_STONE_BRICKS_PLACER)
private val INFESTED_JUMBLE = JumbleBlockPlacer(INFESTED_COBBLESTONE_PLACER, INFESTED_STONE_BRICKS_PLACER, INFESTED_MOSSY_STONE_BRICKS_PLACER, INFESTED_CRACKED_STONE_BRICKS_PLACER)

/** Doors (states are never used elsewhere) */
private val OAK_DOOR = Door(Blocks.OAK_DOOR.defaultState)
private val BIRCH_DOOR = Door(Blocks.BIRCH_DOOR.defaultState)
private val SPRUCE_DOOR = Door(Blocks.SPRUCE_DOOR.defaultState)
private val JUNGLE_DOOR = Door(Blocks.JUNGLE_DOOR.defaultState)
private val ACACIA_DOOR = Door(Blocks.ACACIA_DOOR.defaultState)
private val DARK_OAK_DOOR = Door(Blocks.DARK_OAK_DOOR.defaultState)
private val IRON_DOOR = Door(Blocks.IRON_DOOR.defaultState)

@Suppress("MemberVisibilityCanBePrivate", "CanBeVal", "JoinDeclarationAndAssignment")
object Themes {
    val BLING: Theme
    val BRICK: Theme
    val BUMBO: Theme
    val CAVE: Theme
    val CHECKERS: Theme
    val CRYPT: Theme
    val DARK_HALL: Theme
    val DARK_OAK: Theme
    val ENDER: Theme
    val ENI_ICE: Theme
    val ENIKO: Theme
    val ENIKO_2: Theme
    val ENI_QUARTZ: Theme
    val ETHO: Theme
    val ETHO_TOWER: Theme
    val GREYMERK: Theme
    val HELL: Theme
    val HOUSE: Theme
    //val ICE: Theme
    //val JUNGLE: Theme
    //val MINESHAFT: Theme
    val MOSSY: Theme
    //val MUDDY: Theme
    //val NETHER: Theme
    val OAK: Theme
    //val PURPUR: Theme
    val PYRAMID: Theme
    //val QUARTZ: Theme
    //val RAINBOW: Theme
    //val RED_SANDSTONE: Theme
    //val SANDSTONE: Theme
    //val SEWER: Theme
    //val SNOW: Theme
    val SPRUCE: Theme
    val STONE: Theme
    //val TEMPLE: Theme
    //val TERRACOTTA: Theme
    //val TOWER: Theme

    init {
        // variables for enforcing type safety
        var floor: IBlockPlacer
        var walls: IBlockPlacer
        var stairs: StairsBlockPlacer
        var pillar: IBlockPlacer
        var segmentWall: IBlockPlacer
        var segmentStair: StairsBlockPlacer
        var primary: BlockPalette
        var secondary: BlockPalette

        // bling theme
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(IRON_BLOCK_PLACER, 10), WeightedEntry(GOLD_BLOCK_PLACER, 3), WeightedEntry(EMERALD_BLOCK_PLACER, 10), WeightedEntry(DIAMOND_BLOCK_PLACER, 20)))
        stairs = QUARTZ_STAIRS_PLACER
        pillar = LAPIS_BLOCK_PLACER

        primary = palette(walls, stairs, pillar)

        BLING = theme(primary)

        // brick theme
        walls = BRICKS_PLACER
        stairs = BRICK_STAIRS_PLACER
        pillar = VERTICAL_SPRUCE_LOG_PLACER
        segmentWall = SPRUCE_PLANKS_PLACER

        primary = palette(walls, stairs, pillar)
        secondary = palette(segmentWall, stairs, pillar)

        BRICK = theme(primary, secondary)

        // bumbo theme
        floor = BLACK_TERRACOTTA_PLACER
        walls = GREEN_TERRACOTTA_PLACER
        stairs = ACACIA_STAIRS_PLACER
        pillar = WHITE_CONCRETE_PLACER

        primary = palette(floor, walls, stairs, pillar)

        floor = RED_CONCRETE_PLACER
        walls = YELLOW_CONCRETE_PLACER
        pillar = BLACK_CONCRETE_PLACER

        secondary = palette(floor, walls, stairs, pillar)

        BUMBO = theme(primary, secondary)

        // cave theme
        floor = JumbleBlockPlacer(GRAVEL_PLACER, DIRT_PLACER, COBBLESTONE_PLACER)
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(SMOOTH_STONE_PLACER, 1000), WeightedEntry(DIRT_PLACER, 100), WeightedEntry(GRAVEL_PLACER, 50), WeightedEntry(COBBLESTONE_PLACER, 20), WeightedEntry(COAL_ORE_PLACER, 10), WeightedEntry(IRON_ORE_PLACER, 5), WeightedEntry(EMERALD_ORE_PLACER, 2), WeightedEntry(DIAMOND_ORE_PLACER, 1), WeightedEntry(WATER_PLACER, 3), WeightedEntry(LAVA_PLACER, 1)))
        stairs = COBBLESTONE_STAIRS_PLACER
        pillar = COBBLESTONE_PLACER

        primary = palette(floor, walls, stairs, pillar)

        CAVE = theme(primary)

        // checkers theme
        walls = CheckeredBlockPlacer(OBSIDIAN_PLACER, QUARTZ_BLOCK_PLACER)
        stairs = QUARTZ_STAIRS_PLACER

        primary = palette(walls, stairs, walls)

        CHECKERS = theme(primary)

        // crypt theme
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(STONE_BRICK_JUMBLE, 100), WeightedEntry(COBBLESTONE_PLACER, 10), WeightedEntry(GRAVEL_PLACER, 5)))
        pillar = CheckeredBlockPlacer(ANDESITE_PLACER, POLISHED_ANDESITE_PLACER)
        stairs = STONE_BRICK_STAIRS_PLACER

        primary = palette(walls, walls, stairs, pillar, IRON_DOOR)

        CRYPT = theme(primary)

        // dark hall theme
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(COBBLESTONE_PLACER, 30), WeightedEntry(MOSSY_COBBLESTONE_PLACER, 10), WeightedEntry(STONE_BRICKS_PLACER, 20), WeightedEntry(CRACKED_STONE_BRICKS_PLACER, 10), WeightedEntry(GRAVEL_PLACER, 5)))
        stairs = STONE_BRICK_STAIRS_PLACER
        pillar = MOSSY_STONE_BRICKS_PLACER

        primary = palette(walls, walls, stairs, pillar, DARK_OAK_DOOR)

        walls = DARK_OAK_PLANKS_PLACER
        stairs = DARK_OAK_STAIRS_PLACER
        pillar = VERTICAL_DARK_OAK_LOG_PLACER

        secondary = palette(walls, walls, stairs, pillar, DARK_OAK_DOOR)

        DARK_HALL = theme(primary, secondary)

        // dark oak theme
        walls = DARK_OAK_PLANKS_PLACER
        stairs = DARK_OAK_STAIRS_PLACER
        pillar = VERTICAL_DARK_OAK_LOG_PLACER

        primary = palette(walls, walls, stairs, pillar, DARK_OAK_DOOR)
        
        DARK_OAK = theme(primary, secondary)

        // ender theme
        floor = StripedBlockPlacer(BROWN_TERRACOTTA_PLACER, YELLOW_TERRACOTTA_PLACER)
        walls = END_STONE_BRICKS_PLACER
        stairs = SANDSTONE_STAIRS_PLACER
        pillar = OBSIDIAN_PLACER

        primary = palette(floor, walls, stairs, pillar)
        secondary = palette(floor, CHISELED_SANDSTONE_PLACER, stairs, pillar)

        ENDER = theme(primary, secondary)

        // eni ice theme
        floor = StripedBlockPlacer(WeightedBlockPlacer(arrayOf(WeightedEntry(PURPLE_TERRACOTTA_PLACER, 100), WeightedEntry(WATER_PLACER, 5), WeightedEntry(LAPIS_BLOCK_PLACER, 1))), WeightedBlockPlacer(arrayOf(WeightedEntry(OBSIDIAN_PLACER, 100), WeightedEntry(LAVA_PLACER, 5), WeightedEntry(REDSTONE_BLOCK_PLACER, 1))))
        walls = PACKED_ICE_PLACER
        stairs = QUARTZ_STAIRS_PLACER
        pillar = VERTICAL_QUARTZ_PILLAR_PLACER

        primary = palette(floor, walls, stairs, pillar)

        ENI_ICE = theme(primary)

        // eniko theme
        floor = StripedBlockPlacer(LIGHT_BLUE_TERRACOTTA_PLACER, WHITE_TERRACOTTA_PLACER)
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(STONE_BRICKS_PLACER, 100), WeightedEntry(CRACKED_STONE_BRICKS_PLACER, 1), WeightedEntry(MOSSY_STONE_BRICKS_PLACER, 5)))
        stairs = STONE_BRICK_STAIRS_PLACER
        pillar = SMOOTH_STONE_PLACER

        primary = palette(floor, walls, stairs, pillar)

        ENIKO = theme(primary)

        // eniko 2 theme
        floor = StripedBlockPlacer(BLUE_TERRACOTTA_PLACER, LIGHT_GRAY_TERRACOTTA_PLACER)
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(STONE_BRICKS_PLACER, 20), WeightedEntry(CRACKED_STONE_BRICKS_PLACER, 10), WeightedEntry(MOSSY_STONE_BRICKS_PLACER, 5), WeightedEntry(COBBLESTONE_PLACER, 3), WeightedEntry(GRAVEL_PLACER, 1),))
        stairs = STONE_BRICK_STAIRS_PLACER
        pillar = SMOOTH_STONE_PLACER

        primary = palette(floor, walls, stairs, pillar)

        ENIKO_2 = theme(primary)

        // eni quartz theme
        floor = StripedBlockPlacer(WeightedBlockPlacer(arrayOf(WeightedEntry(RED_TERRACOTTA_PLACER, 100), WeightedEntry(WATER_PLACER, 5), WeightedEntry(REDSTONE_BLOCK_PLACER, 1))), OBSIDIAN_PLACER)
        walls = BRICKS_PLACER
        stairs = BRICK_STAIRS_PLACER

        primary = palette(floor, walls, stairs, walls)

        walls = QUARTZ_BLOCK_PLACER
        stairs = QUARTZ_STAIRS_PLACER
        pillar = VERTICAL_QUARTZ_PILLAR_PLACER

        secondary = palette(floor, walls, stairs, pillar)

        ENI_QUARTZ = theme(primary, secondary)

        // etho theme
        floor = GRASS_BLOCK_PLACER
        walls = OAK_PLANKS_PLACER
        stairs = OAK_STAIRS_PLACER
        pillar = VERTICAL_OAK_LOG_PLACER

        primary = palette(floor, walls, stairs, pillar)

        ETHO = theme(primary)

        // etho tower theme
        walls = CYAN_TERRACOTTA_PLACER
        stairs = SANDSTONE_STAIRS_PLACER

        primary = palette(walls, walls, stairs, walls)

        walls = CUT_SANDSTONE_PLACER

        secondary = palette(walls, walls, stairs, walls)

        ETHO_TOWER = theme(primary, secondary)

        // greymerk theme
        floor = ANDESITE_PLACER
        walls = POLISHED_ANDESITE_PLACER
        stairs = STONE_BRICK_STAIRS_PLACER

        primary = palette(floor, walls, stairs, walls)

        GREYMERK = theme(primary)

        // hell theme
        walls = WeightedBlockPlacer(arrayOf(
            WeightedEntry(NETHER_BRICKS_PLACER, 200),
            WeightedEntry(NETHERRACK_PLACER, 20),
            WeightedEntry(NETHER_QUARTZ_ORE_PLACER, 20),
            WeightedEntry(NETHER_WART_BLOCK_PLACER, 10),
            WeightedEntry(COAL_BLOCK_PLACER, 5),
        ))

        val list = arrayListOf<WeightedEntry<IBlockPlacer>>(
            WeightedEntry(walls, 1500),
            WeightedEntry(RED_NETHER_BRICKS_PLACER, 500),
            WeightedEntry(REDSTONE_BLOCK_PLACER, 500),
        )
        if (Config.preciousBlocks) {
            list.add(WeightedEntry(GOLD_BLOCK_PLACER, 2))
            list.add(WeightedEntry(DIAMOND_BLOCK_PLACER, 1))
        }

        floor = WeightedBlockPlacer(list.toTypedArray())
        stairs = NETHER_BRICK_STAIRS_PLACER
        pillar = OBSIDIAN_PLACER

        primary = palette(floor, walls, stairs, pillar, IRON_DOOR, GLOWSTONE_PLACER, LAVA_PLACER)

        walls = RED_NETHER_BRICKS_PLACER
        pillar = MAGMA_BLOCK_PLACER

        secondary = palette(floor, walls, stairs, pillar, IRON_DOOR, GLOWSTONE_PLACER, LAVA_PLACER)

        HELL = theme(primary, secondary)

        // house theme
        floor = POLISHED_GRANITE_PLACER
        walls = BRICKS_PLACER
        stairs = BRICK_STAIRS_PLACER
        pillar = POLISHED_GRANITE_PLACER

        primary = palette(floor, walls, stairs, pillar)

        floor = POLISHED_ANDESITE_PLACER
        walls = OAK_PLANKS_PLACER
        stairs = OAK_STAIRS_PLACER
        pillar = VERTICAL_OAK_LOG_PLACER

        secondary = palette(floor, walls, stairs, pillar)

        HOUSE = theme(primary, secondary)

        // mossy theme
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(COBBLESTONE_PLACER, 60), WeightedEntry(MOSSY_STONE_BRICKS_PLACER, 30), WeightedEntry(INFESTED_JUMBLE, 5), WeightedEntry(MOSSY_COBBLESTONE_PLACER, 10), WeightedEntry(GRAVEL_PLACER, 15)))
        pillar = WeightedBlockPlacer(arrayOf(WeightedEntry(MOSSY_STONE_BRICKS_PLACER, 20), WeightedEntry(COBBLESTONE_PLACER, 5), WeightedEntry(INFESTED_JUMBLE, 3), WeightedEntry(MOSSY_COBBLESTONE_PLACER, 5)))
        floor = WeightedBlockPlacer(arrayOf(WeightedEntry(MOSSY_COBBLESTONE_PLACER, 10), WeightedEntry(MOSSY_STONE_BRICKS_PLACER, 4), WeightedEntry(COBBLESTONE_PLACER, 2), WeightedEntry(GRAVEL_PLACER, 1)))
        stairs = COBBLESTONE_STAIRS_PLACER

        primary = palette(floor, walls, stairs, pillar, IRON_DOOR)

        MOSSY = theme(primary)

        // oak theme
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(STONE_BRICKS_PLACER, 30), WeightedEntry(CRACKED_STONE_BRICKS_PLACER, 20), WeightedEntry(COBBLESTONE_PLACER, 5), WeightedEntry(GRAVEL_PLACER, 1)))
        stairs = STONE_BRICK_STAIRS_PLACER
        segmentWall = SPRUCE_PLANKS_PLACER
        segmentStair = SPRUCE_STAIRS_PLACER
        pillar = VERTICAL_SPRUCE_LOG_PLACER

        primary = palette(walls, walls, stairs, walls, SPRUCE_DOOR)
        secondary = palette(segmentWall, segmentWall, segmentStair, pillar, SPRUCE_DOOR)

        OAK = theme(primary, secondary)

        // pyramid theme
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(CUT_SANDSTONE_PLACER, 100), WeightedEntry(SANDSTONE_PLACER, 10), WeightedEntry(CHISELED_SANDSTONE_PLACER, 5)))
        stairs = SANDSTONE_STAIRS_PLACER
        pillar = CUT_SANDSTONE_PLACER

        primary = palette(walls, stairs, pillar)
        secondary = palette(CHISELED_SANDSTONE_PLACER, stairs, pillar)

        PYRAMID = theme(primary, secondary)

        // spruce theme
        walls = WeightedBlockPlacer(arrayOf(WeightedEntry(STONE_BRICKS_PLACER, 20), WeightedEntry(CRACKED_STONE_BRICKS_PLACER, 10), WeightedEntry(COBBLESTONE_PLACER, 5), WeightedEntry(GRAVEL_PLACER, 1)))
        stairs = STONE_BRICK_STAIRS_PLACER

        primary = palette(walls, stairs, walls)

        walls = SPRUCE_PLANKS_PLACER
        stairs = SPRUCE_STAIRS_PLACER
        pillar = VERTICAL_SPRUCE_LOG_PLACER

        secondary = palette(walls, stairs, pillar)

        SPRUCE = theme(primary, secondary)

        // stone theme
        walls = STONE_BRICKS_PLACER
        stairs = STONE_BRICK_STAIRS_PLACER
        pillar = POLISHED_ANDESITE_PLACER

        primary = palette(walls, stairs, pillar)

        STONE = theme(primary)
    }
    
    // Theme constructor overloads
    
    private fun theme(palette: BlockPalette): Theme {
        return Theme(palette, palette)
    }

    private fun theme(primary: BlockPalette, second: BlockPalette): Theme {
        return Theme(primary, second)
    }

    // BlockPalette constructor overloads

    private fun palette(floor: IBlockPlacer, wall: IBlockPlacer, stairs: StairsBlockPlacer, pillar: IBlockPlacer, door: Door): BlockPalette {
        return BlockPalette(floor, wall, stairs, pillar, door, GLOWSTONE_PLACER, WATER_PLACER)
    }

    private fun palette(floor: IBlockPlacer, wall: IBlockPlacer, stairs: StairsBlockPlacer, pillar: IBlockPlacer, door: Door, light: IBlockPlacer, fluid: IBlockPlacer): BlockPalette {
        return BlockPalette(floor, wall, stairs, pillar, door, light, fluid)
    }

    private fun palette(floor: IBlockPlacer, wall: IBlockPlacer, stairs: StairsBlockPlacer, pillar: IBlockPlacer): BlockPalette {
        return palette(floor, wall, stairs, pillar, OAK_DOOR)
    }

    private fun palette(wall: IBlockPlacer, stairs: StairsBlockPlacer, pillar: IBlockPlacer): BlockPalette {
        return palette(wall, wall, stairs, pillar)
    }
}