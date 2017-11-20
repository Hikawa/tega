package tk.aankor.tega.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import ktx.ashley.allOf
import ktx.ashley.get
import tk.aankor.tega.components.TiledMapComponent

class TiledMapRenderSystem(override val kodein: Kodein, val camera: OrthographicCamera) :
  IteratingSystem(allOf(TiledMapComponent::class).get(), 0),
  KodeinAware {
  override fun processEntity(entity: Entity, deltaTime: Float) {
    val renderer = entity[TiledMapComponent.mapper]!!.renderer
    renderer.setView(camera)
    renderer.render()
  }
}