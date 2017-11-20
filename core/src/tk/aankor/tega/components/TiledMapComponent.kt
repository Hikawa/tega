package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import ktx.ashley.mapperFor

class TiledMapComponent(
  val map: TiledMap,
  override val kodein: Kodein) : Component, KodeinAware {
  val renderer: TiledMapRenderer = OrthogonalTiledMapRenderer(map, instance<SpriteBatch>())

  companion object {
    val mapper = mapperFor<TiledMapComponent>()
  }
}
