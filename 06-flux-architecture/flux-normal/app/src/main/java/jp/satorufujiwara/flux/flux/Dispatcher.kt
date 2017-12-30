package jp.satorufujiwara.flux.flux

import android.os.Handler
import android.os.Looper
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Dispatcher @Inject constructor() {

  private val handler = Handler(Looper.getMainLooper())
  private val dispatcher = Collections.synchronizedSet(HashSet<((Action<*>) -> Unit)>())

  fun <T> dispatch(action: Action<T>) {
    dispatcher.forEach {
      handler.post {
        it(action)
      }
    }
  }

  fun register(callback: (Action<*>) -> Unit) {
    dispatcher.add(callback)
  }

  fun unregister(callback: (Action<*>) -> Unit) {
    dispatcher.remove(callback)
  }

}