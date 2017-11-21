package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import ktx.ashley.mapperFor

class AccessFilterComponent(val map: TiledMap, val layerNames: Iterable<String>): Component {
  companion object {
    val mapper = mapperFor<AccessFilterComponent>()
  }

  private val accessibility =
    Array(map.properties["width"] as Int, { _ ->
      BooleanArray(map.properties["height"] as Int, { _ -> true }) })

  init {
    layerNames.forEach { layerName ->
      map.layers[layerName].objects.forEach { o ->
        val i = ((o as RectangleMapObject).rectangle.x / map.properties["tilewidth"] as Int).toInt()
        val j = (o.rectangle.y / map.properties["tileheight"] as Int).toInt()
        println("$i $j")
        accessibility[i][j] = false
      }
    }
  }


  fun isAccessible(i: Int, j: Int): Boolean = accessibility[i][j]
}
