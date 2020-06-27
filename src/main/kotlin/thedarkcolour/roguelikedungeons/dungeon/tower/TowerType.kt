package thedarkcolour.roguelikedungeons.dungeon.tower

import thedarkcolour.roguelikedungeons.dungeon.theme.Theme
import thedarkcolour.roguelikedungeons.dungeon.theme.Themes

enum class TowerType(val get: () -> ITower, val theme: () -> Theme) {
	ROGUE(::RogueTower, Themes::OAK),;
    //ENIKO(::EnikoTower, Themes::OAK),
    //ETHO(::EthoTower, Themes::ETHO_TOWER),
    //PYRAMID(::PyramidTower, Themes::PYRAMID),
    //JUNGLE(::JungleTower, Themes::JUNGLE),
    //WITCH(::WitchTower, Themes::DARK_HALL),
    //HOUSE(::HouseTower, Themes::HOUSE),
    //BUNKER(::BunkerTower, Themes::OAK),
    //RUIN(::RuinTower, Themes::OAK),
    //HOLE(::HoleTower, Themes::OAK),
    //TREE(::TreeTower, Themes::OAK),
    //BUMBO(::BumboTower, Themes::BUMBO);
}