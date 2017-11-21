package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import ktx.ashley.mapperFor

class GridComponent(val img: Texture, val width: Int, val height: Int): Component {
  companion object {
    val mapper = mapperFor<GridComponent>()
  }
}