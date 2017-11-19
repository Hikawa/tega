package tk.aankor.tega.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.ashley.mapperFor
import tk.aankor.tega.components.TextureComponent
import tk.aankor.tega.components.TransformComponent

class RenderSystem(override val kodein: Kodein): IteratingSystem(allOf(TextureComponent::class, TransformComponent::class).get()), KodeinAware {
  private val getTransform = mapperFor<TransformComponent>()
  private val getTexture = mapperFor<TextureComponent>()

  override fun processEntity(entity: Entity, deltaTime: Float) {
    val transform = entity[getTransform]!!
    instance<SpriteBatch>().draw(entity[getTexture]!!.img, transform.x, transform.y)
  }
}