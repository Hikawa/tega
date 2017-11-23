package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import ktx.ashley.mapperFor
import tk.aankor.tega.wrappers.height
import tk.aankor.tega.wrappers.tileHeight
import tk.aankor.tega.wrappers.tileWidth
import tk.aankor.tega.wrappers.width

class AccessFilterComponent: Component {
  companion object {
    val mapper = mapperFor<AccessFilterComponent>()
  }

  private lateinit var accessibility: Array<BooleanArray>


  // init helpers
  fun load(map: TiledMap, layerNames: Iterable<String>): AccessFilterComponent {
    accessibility = Array(map.width, { _ ->
      BooleanArray(map.height, { _ -> true }) })
    layerNames.forEach { layerName ->
      map.layers[layerName].objects.forEach { o ->
        val i = ((o as RectangleMapObject).rectangle.x / map.tileWidth).toInt()
        val j = (o.rectangle.y / map.tileHeight).toInt()
        accessibility[i][j] = false
      }
    }
    return this
  }

  fun isAccessible(i: Int, j: Int): Boolean = accessibility[i][j]
}
