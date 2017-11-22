package tk.aankor.tega.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import ktx.app.use
import ktx.ashley.allOf
import ktx.ashley.get
import tk.aankor.tega.components.AccessFilterComponent
import tk.aankor.tega.components.GridComponent
import tk.aankor.tega.components.TiledMapComponent

class TiledMapRenderSystem(override val kodein: Kodein, val camera: OrthographicCamera) :
  IteratingSystem(allOf(TiledMapComponent::class).get(), 1),
  KodeinAware {
  override fun processEntity(entity: Entity, deltaTime: Float) {
    val renderer = entity[TiledMapComponent.mapper]!!
    renderer.renderer.setView(camera)
    renderer.renderer.render()
    val grid = entity[GridComponent.mapper]
    if (grid != null) {
      val accessFilter = entity[AccessFilterComponent.mapper]
      val batch = instance<SpriteBatch>()
      batch.use {
        for (i in 0 until grid.width)
          (0 until grid.height)
            .filter { (accessFilter == null) || accessFilter.isAccessible(i, it) }
            .forEach {
              batch.draw(
                grid.img,
                (i * renderer.map.properties["tilewidth"] as Int).toFloat(),
                (it * renderer.map.properties["tileheight"] as Int).toFloat())
            }
      }
    }
  }
}