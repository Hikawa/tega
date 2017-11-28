package tk.aankor.tega.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import ktx.app.use
import ktx.ashley.get
import ktx.ashley.oneOf
import tk.aankor.tega.components.AnimationComponent
import tk.aankor.tega.components.ResizeComponent
import tk.aankor.tega.components.TextureComponent
import tk.aankor.tega.components.TransformComponent

class SpriteRenderSystem(override val kodein: Kodein)
  : IteratingSystem(oneOf(TextureComponent::class, AnimationComponent::class).get(), 10)
  , KodeinAware {

  private val batch = instance<SpriteBatch>()

  override fun processEntity(entity: Entity, deltaTime: Float) {
    val transform = entity[TransformComponent.mapper]?: TransformComponent()
    val texture = entity[TextureComponent.mapper]
    val animation = entity[AnimationComponent.mapper]
    if (texture != null) {
      val resize = entity[ResizeComponent.mapper]?:
        ResizeComponent().with(
          texture.texture.regionWidth.toFloat(),
          texture.texture.regionHeight.toFloat())
      batch.draw(texture.texture, transform.posiotion.x, transform.posiotion.y, resize.width, resize.height)
    } else if (animation != null) {
      val resize = entity[ResizeComponent.mapper]?:
        ResizeComponent().with(
          animation.frame.regionWidth.toFloat(),
          animation.frame.regionHeight.toFloat())
      batch.draw(animation.frame, transform.posiotion.x, transform.posiotion.y, resize.width, resize.height)
      animation.time += deltaTime
    }
  }

  override fun update(deltaTime: Float) {
    batch.use {
      super.update(deltaTime)
    }
  }
}