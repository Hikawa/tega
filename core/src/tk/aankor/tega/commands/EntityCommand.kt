package tk.aankor.tega.commands

import com.badlogic.ashley.core.Entity
import java.util.*

abstract class EntityCommand(val uuid: UUID) {
  abstract fun process(entity: Entity): Boolean
}