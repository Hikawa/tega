package tk.aankor.tega

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import ktx.app.KtxGame
import ktx.async.enableKtxCoroutines

class Application: KtxGame<Screen>(), KodeinAware {
  override lateinit var kodein: Kodein

  @Override
  override fun create() {
    kodein = Kodein {
      bind<SpriteBatch>() with instance(SpriteBatch())
      bind<TmxMapLoader>() with instance(TmxMapLoader())
    }
    enableKtxCoroutines(0)

    addScreen(BattleScreen(kodein, instance<TmxMapLoader>().load("map1.tmx")))
    setScreen<BattleScreen>()
  }
}
