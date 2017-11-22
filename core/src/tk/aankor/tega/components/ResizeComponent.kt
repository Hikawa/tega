package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class ResizeComponent(val width: Float, val height: Float): Component {
  companion object {
    val mapper = mapperFor<ResizeComponent>()
  }
}