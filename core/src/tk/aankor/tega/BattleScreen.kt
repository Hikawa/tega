package tk.aankor.tega

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import ktx.app.KtxScreen
import ktx.ashley.EngineEntity
import ktx.ashley.add
import ktx.ashley.entity
import tk.aankor.tega.components.TextureComponent
import tk.aankor.tega.components.TiledMapComponent
import tk.aankor.tega.components.TransformComponent
import tk.aankor.tega.systems.TextureRenderSystem
import tk.aankor.tega.systems.TiledMapRenderSystem

fun <T: Component> EngineEntity.component(c: T): EngineEntity {
  entity.add(c)
  return this
}

class BattleScreen(override val kodein: Kodein): KtxScreen, KodeinAware {
  private val batch: SpriteBatch = instance()

  private val ecs = PooledEngine()
  private val worldCamera = OrthographicCamera()
  private val viewPort = FitViewport(800f, 480f, worldCamera)

  init {
    ecs.add {
      entity {
        component(TiledMapComponent(
          instance<TmxMapLoader>().load("map1.tmx"),
          kodein))
      }
      entity {
        component(TransformComponent(0.0f, 0.0f))
        component(TextureComponent(Texture("badlogic.jpg")))
      }
      addSystem(TiledMapRenderSystem(kodein, worldCamera))
      addSystem(TextureRenderSystem(kodein))
    }
  }

  override fun resize(width: Int, height: Int) {
    super.resize(width, height)
    viewPort.update(width, height)
    worldCamera.position.set(400f, 240f, 0f)
  }

  override fun render(delta: Float) {
    Gdx.gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    worldCamera.update()
    batch.projectionMatrix = worldCamera.combined
    ecs.update(delta)
  }
}