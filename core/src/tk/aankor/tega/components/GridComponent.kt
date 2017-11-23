package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import ktx.ashley.mapperFor

class GridComponent: Component {
  lateinit var texture: Texture

  companion object {
    val mapper = mapperFor<GridComponent>()
  }
}