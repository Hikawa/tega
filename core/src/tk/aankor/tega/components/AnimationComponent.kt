package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.ashley.mapperFor
import tk.aankor.tega.resources.loadAnimationSheet

class AnimationComponent: Component {
  var animation: Animation<TextureRegion>? = null
    set(v) {
      if (field != v) time = 0.0f
      field = v
    }
  var endingListener: (() -> Unit)? = null
  var time: Float = 0.0f
    set(v) {
      field = v
      val a = animation
      if ((a != null) && setOf(Animation.PlayMode.NORMAL, Animation.PlayMode.REVERSED).contains(a.playMode) &&
        (field > animation!!.animationDuration)) {
        val listener = endingListener
        if (listener != null) listener()
      }
    }

  val frame: TextureRegion
    get() = animation!!.getKeyFrame(time)


  // init helpers
  fun load(duration: Float, vararg frames: TextureRegion): AnimationComponent {
    animation = Animation(duration, *frames)
    return this
  }

  fun load(
    frameDuration: Float,
    atlasFileName: String,
    playMode: Animation.PlayMode = Animation.PlayMode.NORMAL)
    : AnimationComponent
  {
    animation = loadAnimationSheet(frameDuration, atlasFileName, playMode)
    return this
  }

  companion object {
    val mapper = mapperFor<AnimationComponent>()
  }
}
