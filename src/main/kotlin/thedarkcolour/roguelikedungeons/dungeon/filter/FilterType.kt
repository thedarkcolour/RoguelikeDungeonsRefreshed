package thedarkcolour.roguelikedungeons.dungeon.filter

import thedarkcolour.roguelikedungeons.Complete

@Complete
enum class FilterType(val get: () -> IFilter) {
    VINE(::VineFilter),
    ENCASE(::EncaseFilter),
    WIREFRAME(::WireframeFilter),
    COBWEB(::CobwebFilter),
    MUD(::MudFilter),
}