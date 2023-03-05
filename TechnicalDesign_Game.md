# BattleMap

## Features
```plantuml
@startuml

interface       Serializable



abstract MapFeature {
    Location location
    boolean isActive
    
    activate()
    deactivate()
    addPlayer()
    removePlayer()
}

class ControlPoint {

}

class ManaWell {

}

class CTFBase {

}

class SpawnPoint {

}

class SpectatorSpawn {

}

class Lobby {

}

Serializable <-- MapFeature
MapFeature <-- ControlPoint
MapFeature <-- ManaWell
MapFeature <-- CTFBase
MapFeature <-- SpawnPoint
MapFeature <-- SpectatorSpawn
MapFeature <-- Lobby
@enduml
```

## Kits

## GameModes
This plugin supports multiple gamemodes. For each gamemode there can be multiple maps. Each map has to be initialized for a certain gamemode in order for the plugin to know what to do.

```plantuml
@startuml

interface Gamemode
interface Serializable {
    load()
    save()
}

class BattleMapCatalog {
    List<Map> maps
}

class Map {
    Gamemode gamemode
    int mapId
    List<Player> players
}


abstract Gamemode {
    List<BattleMapFeature> features
    List<Team> teams

    
    activate()
    deactivate()
    determineWinner()
    addPlayer()
    removePlayer()
}

class CTF {

}

class DeathMatch {

}

class ControlPoints {

}

class Conquest {

}

Serializable <-- BattleMapCatalog
BattleMapCatalog <-- Map
Map <-- Gamemode

Gamemode <-- DeathMatch
Gamemode <-- ControlPoints
Gamemode <-- Conquest
Gamemode <-- CTF




@enduml
```

## Commands
### /center
### /controlpoint