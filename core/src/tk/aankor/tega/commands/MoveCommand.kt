package tk.aankor.tega.commands

import com.badlogic.ashley.core.Entity
import ktx.ashley.get
import tk.aankor.tega.components.TransformComponent
import java.util.*

class MoveCommand(uuid: UUID, val x: Float, val y: Float): EntityCommand(uuid) {
  override fun process(entity: Entity): Boolean {
    val transform = entity[TransformComponent.mapper]!!
    transform.x = x
    transform.y = y
    return true
  }
}
