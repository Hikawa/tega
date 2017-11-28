package tk.aankor.tega.resources

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2


class AnimationPack {
  lateinit var fwd: Animation<TextureRegion>
  lateinit var bwd: Animation<TextureRegion>
  lateinit var left: Animation<TextureRegion>
  lateinit var right: Animation<TextureRegion>

  fun load(duration: Float, baseFileName: String, playMode: Animation.PlayMode = Animation.PlayMode.NORMAL): AnimationPack {
    fwd = loadAnimationSheet(duration, baseFileName + "_fwd.txt", playMode)
    bwd = loadAnimationSheet(duration, baseFileName + "_bwd.txt", playMode)
    left = loadAnimationSheet(duration, baseFileName + "_left.txt", playMode)
    right = loadAnimationSheet(duration, baseFileName + "_right.txt", playMode)
    return this
  }

  operator fun get(dir: Vector2): Animation<TextureRegion> {
    return if (dir.x == 0.0f)
      if (dir.y > 0.0f) bwd else fwd
    else if (dir.x < 0)
      left
    else
      right
  }
}
