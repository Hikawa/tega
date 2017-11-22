package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.ashley.mapperFor

class AnimationComponent(val animation: Animation<TextureRegion>, val looping: Boolean = true): Component {
  companion object {
    val mapper = mapperFor<AnimationComponent>()
  }

  var time = 0.0f
  var active = true

  val frame: TextureRegion
    get() = animation.getKeyFrame(time, looping)
}
