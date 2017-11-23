package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class ResizeComponent: Component {
  var width: Float = 1.0f
  var height: Float = 1.0f

  fun with(width: Float, height: Float): ResizeComponent {
    this.width = width
    this.height = height
    return this
  }

  companion object {
    val mapper = mapperFor<ResizeComponent>()
  }
}