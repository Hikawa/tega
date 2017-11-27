package tk.aankor.tega.commands

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import ktx.ashley.get
import ktx.math.minus
import ktx.math.plus
import ktx.math.times
import tk.aankor.tega.components.AnimationComponent
import tk.aankor.tega.components.IdentityComponent
import tk.aankor.tega.components.TransformComponent
import tk.aankor.tega.resources.AnimationPack
import java.util.*

class MoveCommand: EntityCommand {
  lateinit var uuid: UUID
  lateinit var path: Iterable<Vector2>
  private var iter: Iterator<Vector2>? = null
  private var next: Vector2? = null
  var speed: Float = 0f
  lateinit var animationPack: AnimationPack
  var nextCommand: EntityCommand? = null

  fun with(f: MoveCommand.() -> Unit): MoveCommand {
    f(this)
    return this
  }

  override fun process(entity: Entity): EntityCommand? {
    // initialization
    if (iter == null) {
      iter = path.iterator()
      next = if (iter!!.hasNext()) iter!!.next() else null
    }

    val identity = entity[IdentityComponent.mapper]
    if (uuid != identity?.uuid) return this

    val transform = entity[TransformComponent.mapper]!!
    val n = next?: return nextCommand

    val dir = (n.cpy() - transform.posiotion).nor() * speed
    val animation = entity[AnimationComponent.mapper]
    if (animation != animationPack[dir]) {
      if (animation == null)
        entity.remove(AnimationComponent::class.java)
      entity.add(animationPack[dir])
    }

    if ((n.cpy() - transform.posiotion).len() < speed) {
      transform.posiotion = n
      next = if (iter!!.hasNext()) iter!!.next() else null
      return if (next != null) this else nextCommand
    }

    transform.posiotion += dir
    return this
  }
}
