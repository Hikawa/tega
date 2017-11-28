package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import io.github.aedans.cons.Cons
import io.github.aedans.cons.Nil
import ktx.ashley.mapperFor
import tk.aankor.tega.resources.AnimationPack

class MovingComponent: Component {
  var path: Cons<Vector2> = Nil
  var speed: Float = 0f
  lateinit var animationPack: AnimationPack

  companion object {
    val mapper = mapperFor<MovingComponent>()
  }
}
