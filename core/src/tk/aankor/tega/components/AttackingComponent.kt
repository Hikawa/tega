package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor
import tk.aankor.tega.resources.AnimationPack

class AttackingComponent : Component {
  lateinit var target: Vector2
  lateinit var animationPack: AnimationPack

  companion object {
    val mapper = mapperFor<AttackingComponent>()
  }
}