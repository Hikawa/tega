package tk.aankor.tega

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import ktx.app.KtxScreen
import ktx.app.use
import ktx.ashley.EngineEntity
import ktx.ashley.add
import ktx.ashley.entity
import tk.aankor.tega.components.TextureComponent
import tk.aankor.tega.components.TransformComponent
import tk.aankor.tega.systems.RenderSystem

fun <T: Component> EngineEntity.component(c: T): EngineEntity {
  entity.add(c)
  return this
}

class BattleScreen(override val kodein: Kodein): KtxScreen, KodeinAware {
  private val batch: SpriteBatch = instance()

  private val engine = PooledEngine()

  init {
    engine.add {
      entity {
        component(TransformComponent(0.0f, 0.0f))
        component(TextureComponent(Texture("badlogic.jpg")))
      }

      addSystem(RenderSystem(kodein))
    }
  }

  override fun render(delta: Float) {
    Gdx.gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.use {
      engine.update(delta)
    }
  }
}