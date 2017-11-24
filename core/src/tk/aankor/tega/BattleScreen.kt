package tk.aankor.tega

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import ktx.app.KtxScreen
import ktx.ashley.add
import ktx.ashley.entity
import tk.aankor.tega.commands.AttackCommand
import tk.aankor.tega.commands.MoveCommand
import tk.aankor.tega.components.*
import tk.aankor.tega.resources.AnimationPack
import tk.aankor.tega.systems.CommandSystem
import tk.aankor.tega.systems.SpriteRenderSystem
import tk.aankor.tega.systems.TiledMapRenderSystem
import tk.aankor.tega.wrappers.height
import tk.aankor.tega.wrappers.tileHeight
import tk.aankor.tega.wrappers.tileWidth
import tk.aankor.tega.wrappers.width
import java.util.*


class BattleScreen(override val kodein: Kodein, map: TiledMap): KtxScreen, KodeinAware {
  private val batch: SpriteBatch = instance()

  private val ecs = PooledEngine()
  private val worldCamera = OrthographicCamera()
  private val viewPort = FitViewport(
    (map.width * map.tileWidth).toFloat(),
    (map.height * map.tileHeight).toFloat(),
    worldCamera)

  val commandSystem = CommandSystem()

  init {

    ecs.add {
      entity {
        with<TiledMapComponent> {
          load(kodein, map)
        }
        with<GridComponent> {
          texture = Texture("border.png")
        }
        with<AccessFilterComponent> {
          load(map, listOf("Rock", "Tree"))
        }
      }
      entity {
        with<IdentityComponent> {
          uuid = UUID.fromString("067e6162-3b6f-4ae2-a171-2470b63dff00")
        }
        with<TransformComponent> {
          posiotion = Vector2(128.0f, 128.0f)
        }
        with<AnimationComponent> {
          load(Float.POSITIVE_INFINITY, "char1/move_fwd.txt")
        }
        with<ResizeComponent> {
          width = 32.0f
          height = 32.0f
        }
      }
      addSystem(TiledMapRenderSystem(kodein, worldCamera))
      addSystem(SpriteRenderSystem(kodein))
      addSystem(commandSystem)
      commandSystem.add(MoveCommand(
        UUID.fromString("067e6162-3b6f-4ae2-a171-2470b63dff00")).with {
        path = listOf(
          Vector2(32f, 128f),
          Vector2(32f, 32f),
          Vector2(128f, 32f),
          Vector2(128f, 128f))
        speed = 0.4f
        animationPack = AnimationPack().load(1f/ 30f, "char1/move")
        nextCommand = AttackCommand(
          UUID.fromString("067e6162-3b6f-4ae2-a171-2470b63dff00")).with {
          target = Vector2(256f, 128f)
          animationPack = AnimationPack().load(1f/ 30f, "char1/kick", false, true)
        }
      })
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