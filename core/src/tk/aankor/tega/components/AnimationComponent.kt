package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.ashley.mapperFor

class AnimationComponent: Component {
  lateinit var animation: Animation<TextureRegion>
  var looping: Boolean = true
  var time = 0.0f
  var active = true

  val frame: TextureRegion
    get() = animation.getKeyFrame(time, looping)

  // init helpers
  fun load(duration: Float, vararg frames: TextureRegion): AnimationComponent {
    animation = Animation(duration, *frames)
    return this
  }

  companion object {
    val mapper = mapperFor<AnimationComponent>()
  }
}
