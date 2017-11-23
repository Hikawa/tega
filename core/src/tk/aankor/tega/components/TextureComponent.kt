package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.ashley.mapperFor

class TextureComponent: Component {
  lateinit var texture: TextureRegion

  // init helpers
  fun load(fileName: String): TextureComponent {
    texture = TextureRegion(Texture(fileName))
    return this
  }

  companion object {
    val mapper = mapperFor<TextureComponent>()
  }
}