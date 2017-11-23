package tk.aankor.tega.wrappers

import com.badlogic.ashley.core.Entity
import ktx.ashley.get
import tk.aankor.tega.components.IdentityComponent

class EntityHandle(private val entity: Entity) {
  val uuid = entity[IdentityComponent.mapper]!!.uuid

  fun get(): Entity? {
    val iden = entity[IdentityComponent.mapper]?: return null
    return if (iden.uuid == uuid) entity
    else null
  }
}