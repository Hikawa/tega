package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class TransformComponent: Component {
  var x: Float = 0.0f
  var y: Float = 0.0f

  companion object {
    val mapper = mapperFor<TransformComponent>()
  }
}