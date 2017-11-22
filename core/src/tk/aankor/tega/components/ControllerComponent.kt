package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class ControllerComponent: Component {
  companion object {
    val mapper = mapperFor<ControllerComponent>()
  }
}