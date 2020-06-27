package thedarkcolour.roguelikedungeons.dungeon.segment

import thedarkcolour.roguelikedungeons.Complete

@Complete
enum class SegmentType(val get: () -> ISegment) {
    ARCH(::ArchSegment), FIRE_ARCH(::FireArchSegment), FIREPLACE(::FireplaceSegment),
	SHELF(::ShelfSegment), INSET(::InsetSegment), MOSSY_ARCH(::MossyArchSegment),
	MUSHROOM(::MushroomSegment), NETHER_ARCH(::NetherArchSegment), NETHER_STRIPE(::NetherStripeSegment),
	NETHER_WART(::NetherWarSegment), NETHER_LAVA(::NetherLavaSegment), //JUNGLE(::JungleSegment),
	BOOKS(::BooksSegment), SPAWNER(::SpawnerSegment), WHEAT(::WheatSegment),
	TOMB(::TombSegment), CHEST(::ChestSegment), SILVERFISH(::SilverfishSegment),
	SKULL(::SkullSegment), FLOWERS(::FlowerSegment), DOOR(::DoorSegment),
	//ANKH(::AnkhSegment), CAVE(::CaveSegment), SEWER(::SewerSegment),
	//SEWER_ARCH(::SewerArchSegment), SEWER_DOOR(::SewerDoorSegment), SEWER_DRAIN(::SewerDrainSegment),
	//MINESHAFT(::MineshaftSegment),
    LAMP(::LampSegment),
    ARROW(::ArrowSegment),
	//SQUARE_ARCH(::SquareArch),
    CELL(::CellSegment),
	WALL(::WallSegment),
    //PLANT(::PlantSegment)
    ;

    companion object {
        /** All the segments except for [ARCH], [FIRE_ARCH], and [NETHER_ARCH]. */
        val SEGMENTS = arrayOf(
			FIREPLACE, SHELF, INSET, MOSSY_ARCH, //MUSHROOM,
			//NETHER_STRIPE, NETHER_WART, NETHER_LAVA, JUNGLE, BOOKS, SPAWNER,
			//WHEAT, TOMB, CHEST, SILVERFISH, SKULL, FLOWERS, DOOR, ANKH, CAVE,
			//SEWER, SEWER_ARCH, SEWER_DOOR, SEWER_DRAIN, MINESHAFT, LAMP, ARROW, SQUARE_ARCH,
			//CELL, WALL, PLANT
		)
    }
}