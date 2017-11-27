package tk.aankor.tega.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.allOf
import tk.aankor.tega.commands.EntityCommand
import tk.aankor.tega.components.IdentityComponent

class CommandSystem: IteratingSystem(allOf(IdentityComponent::class).get(), 0) {
  private var queue = listOf<EntityCommand>()

  override fun update(deltaTime: Float) {
    if (queue.isNotEmpty()) {
      super.update(deltaTime)
    }
  }

  override fun processEntity(entity: Entity, deltaTime: Float) {
    //val identity = entity[IdentityComponent.mapper]!!
    queue = queue.mapNotNull { c -> c.process(entity) }
  }

  fun add(c: EntityCommand): CommandSystem {
    queue += c
    return this
  }

}