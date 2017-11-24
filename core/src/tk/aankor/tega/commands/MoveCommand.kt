package tk.aankor.tega.commands

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import ktx.ashley.get
import ktx.async.ktxAsync
import ktx.math.minus
import ktx.math.plus
import ktx.math.times
import tk.aankor.tega.components.AnimationComponent
import tk.aankor.tega.components.TransformComponent
import tk.aankor.tega.resources.AnimationPack
import java.util.*

class MoveCommand(uuid: UUID): EntityCommand(uuid) {
  lateinit var path: Iterable<Vector2>
  var speed: Float = 0f
  lateinit var animationPack: AnimationPack
  var nextCommand: EntityCommand? = null

  fun with(f: MoveCommand.() -> Unit): MoveCommand {
    f(this)
    return this
  }

  override fun process(entity: Entity): Boolean {
    val transform = entity[TransformComponent.mapper]!!
    val oldAnimation = entity[AnimationComponent.mapper]!!
    ktxAsync {
      path.forEach { p ->
        val dir = (p.cpy() - transform.posiotion).nor() * speed
        entity.remove(AnimationComponent::class.java)
        entity.add(animationPack[dir])
        while ((p.cpy() - transform.posiotion).len() > speed) {
          transform.posiotion += dir
          skipFrame()
        }
        transform.posiotion = p
        skipFrame()
      }
      val next = nextCommand
      if (next != null)
        next.process(entity)
      else {
        entity.remove(AnimationComponent::class.java)
        entity.add(oldAnimation)
      }
    }
    return true
  }
}
