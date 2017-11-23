package tk.aankor.tega.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import ktx.ashley.mapperFor

class TiledMapComponent: Component {
  lateinit var map: TiledMap
  lateinit var renderer: TiledMapRenderer

  // init helpers
  fun loadTmx(kodein: Kodein, fileName: String): TiledMapComponent {
    map = kodein.instance<TmxMapLoader>().load(fileName)
    renderer = OrthogonalTiledMapRenderer(map, kodein.instance<SpriteBatch>())
    return this
  }

  fun load(kodein: Kodein, map: TiledMap): TiledMapComponent {
    this.map = map
    renderer = OrthogonalTiledMapRenderer(map, kodein.instance<SpriteBatch>())
    return this
  }

  companion object {
    val mapper = mapperFor<TiledMapComponent>()
  }
}
