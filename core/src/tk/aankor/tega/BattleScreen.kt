package tk.aankor.tega

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import ktx.app.KtxScreen
import ktx.ashley.EngineEntity
import ktx.ashley.add
import ktx.ashley.entity
import tk.aankor.tega.components.*
import tk.aankor.tega.systems.SpriteRenderSystem
import tk.aankor.tega.systems.TiledMapRenderSystem

fun <T: Component> EngineEntity.component(c: T): EngineEntity {
  entity.add(c)
  return this
}

class BattleScreen(override val kodein: Kodein, map: TiledMap): KtxScreen, KodeinAware {
  private val batch: SpriteBatch = instance()
  private val width = map.properties["width"] as Int
  private val height = map.properties["height"] as Int
  private val tileWidth = map.properties["tilewidth"] as Int
  private val tileHeight = map.properties["tileheight"] as Int

  private val ecs = PooledEngine()
  private val worldCamera = OrthographicCamera()
  private val viewPort = FitViewport(
    (width * tileWidth).toFloat(),
    (height * tileHeight).toFloat(), worldCamera)

  init {

    ecs.add {
      entity {
        component(TiledMapComponent(
          map,
          kodein))
        component(GridComponent(
          Texture("border.png"),
          width, height))
        component(AccessFilterComponent(width, height).load(map, listOf("Rock", "Tree")))
      }
      entity {
        component(TransformComponent(8.0f, 0.0f))
        val spritesheet = Texture("malebase.png")
        component(AnimationComponent(Animation(0.1f, TextureRegion(spritesheet, 32, 32, 32, 64))))
        component(ResizeComponent(16.0f, 32.0f))
      }
      addSystem(TiledMapRenderSystem(kodein, worldCamera))
      addSystem(SpriteRenderSystem(kodein))
    }
  }

  override fun resize(width: Int, height: Int) {
    super.resize(width, height)
    viewPort.update(width, height)
    worldCamera.position.set(viewPort.worldWidth / 2.0f, viewPort.worldHeight / 2.0f, 0f)
  }

  override fun render(delta: Float) {
    Gdx.gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    worldCamera.update()
    batch.projectionMatrix = worldCamera.combined
    ecs.update(delta)
  }
}