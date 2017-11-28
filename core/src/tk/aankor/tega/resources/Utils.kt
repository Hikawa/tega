package tk.aankor.tega.resources

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

fun loadAnimationSheet(frameDuration: Float, fileName: String, playMode: Animation.PlayMode): Animation<TextureRegion> {
  val atlas = TextureAtlas(fileName)
  return Animation(frameDuration, atlas.regions, playMode)
}