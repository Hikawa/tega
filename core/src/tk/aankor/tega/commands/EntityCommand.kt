package tk.aankor.tega.commands

import com.badlogic.ashley.core.Entity

interface EntityCommand {
  fun process(entity: Entity): EntityCommand? // replaced by
}