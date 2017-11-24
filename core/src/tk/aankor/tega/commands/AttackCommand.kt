package tk.aankor.tega.commands

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import ktx.ashley.get
import ktx.math.minus
import tk.aankor.tega.components.AnimationComponent
import tk.aankor.tega.components.TransformComponent
import tk.aankor.tega.resources.AnimationPack
import java.util.*

class AttackCommand(uuid: UUID): EntityCommand(uuid) {
  lateinit var target: Vector2
  lateinit var animationPack: AnimationPack

  fun with(f: AttackCommand.() -> Unit): AttackCommand {
    f(this)
    return this
  }

  override fun process(entity: Entity): Boolean {
    entity.remove(AnimationComponent::class.java)
    val transform = entity[TransformComponent.mapper]!!
    entity.add(animationPack[target.cpy() - transform.posiotion])
    return true
  }

}