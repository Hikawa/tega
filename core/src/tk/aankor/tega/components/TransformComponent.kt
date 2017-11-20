package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class TransformComponent(var x: Float, var y: Float) : Component {
  companion object {
    val mapper = mapperFor<TransformComponent>()
  }
}