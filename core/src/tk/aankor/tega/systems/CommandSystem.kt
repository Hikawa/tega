package tk.aankor.tega.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.allOf
import ktx.ashley.get
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
    val identity = entity[IdentityComponent.mapper]!!
    queue = queue.filter { c ->
      if (c.uuid == identity.uuid)
        !c.process(entity)
      else true
    }
  }

  fun add(c: EntityCommand): CommandSystem {
    queue += c
    return this
  }

}