package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor
import java.util.*

class IdentityComponent : Component {
  lateinit var uuid: UUID

  companion object {
    val mapper = mapperFor<IdentityComponent>()
  }
}