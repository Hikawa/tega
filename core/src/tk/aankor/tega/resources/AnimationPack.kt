package tk.aankor.tega.resources

import com.badlogic.gdx.math.Vector2
import tk.aankor.tega.components.AnimationComponent

class AnimationPack {
  private var fwd = AnimationComponent()
  private var bwd = AnimationComponent()
  private var left = AnimationComponent()
  private var right = AnimationComponent()

  fun load(duration: Float, baseFileName: String, looping: Boolean = true, mirror: Boolean = false): AnimationPack {
    run {
      fwd.looping = looping
      fwd.load(duration, baseFileName + "_fwd.txt", mirror)
    }
    run {
      bwd.looping = looping
      bwd.load(duration, baseFileName + "_bwd.txt", mirror)
    }
    run {
      left.looping = looping
      left.load(duration, baseFileName + "_left.txt", mirror)
    }
    run {
      right.looping = looping
      right.load(duration, baseFileName + "_right.txt", mirror)
    }
    return this
  }

  operator fun get(dir: Vector2): AnimationComponent {
    return if (dir.x == 0.0f)
      if (dir.y > 0.0f) bwd else fwd
    else if (dir.x < 0)
      left
    else
      right
  }
}
