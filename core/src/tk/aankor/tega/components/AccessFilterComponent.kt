package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import ktx.ashley.mapperFor

class AccessFilterComponent(val width: Int, val height: Int): Component {
  companion object {
    val mapper = mapperFor<AccessFilterComponent>()
  }

  private val accessibility =
    Array(width, { _ ->
      BooleanArray(height, { _ -> true }) })

  fun load(map: TiledMap, layerNames: Iterable<String>): AccessFilterComponent {
    layerNames.forEach { layerName ->
      map.layers[layerName].objects.forEach { o ->
        val i = ((o as RectangleMapObject).rectangle.x / map.properties["tilewidth"] as Int).toInt()
        val j = (o.rectangle.y / map.properties["tileheight"] as Int).toInt()
        accessibility[i][j] = false
      }
    }
    return this
  }

  fun isAccessible(i: Int, j: Int): Boolean = accessibility[i][j]
}
