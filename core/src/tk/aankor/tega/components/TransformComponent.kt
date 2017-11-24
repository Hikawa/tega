package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor

class TransformComponent: Component {
  var posiotion = Vector2(0.0f, 0.0f)

  companion object {
    val mapper = mapperFor<TransformComponent>()
  }
}