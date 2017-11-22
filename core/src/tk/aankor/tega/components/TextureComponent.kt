package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.ashley.mapperFor

class TextureComponent(val texture: TextureRegion): Component {
  companion object {
    val mapper = mapperFor<TextureComponent>()
  }
}