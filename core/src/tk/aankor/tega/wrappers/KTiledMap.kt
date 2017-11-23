package tk.aankor.tega.wrappers

import com.badlogic.gdx.maps.tiled.TiledMap

val TiledMap.width
  get() = properties["width"] as Int

val TiledMap.height
  get() = properties["height"] as Int

val TiledMap.tileWidth
  get() = properties["tilewidth"] as Int

val TiledMap.tileHeight
  get() = properties["tileheight"] as Int