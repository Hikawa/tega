package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import ktx.ashley.mapperFor

class TextureComponent(val img: Texture) : Component {
  companion object {
    val mapper = mapperFor<TextureComponent>()
  }
}