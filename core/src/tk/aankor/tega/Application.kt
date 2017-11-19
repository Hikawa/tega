package tk.aankor.tega

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import ktx.app.KtxGame

class Application: KtxGame<Screen>(), KodeinAware {
  override lateinit var kodein: Kodein

  @Override
  override fun create() {
    kodein = Kodein {
      bind<SpriteBatch>() with instance(SpriteBatch())
    }



    addScreen(BattleScreen(kodein))
    setScreen<BattleScreen>()
  }
}
