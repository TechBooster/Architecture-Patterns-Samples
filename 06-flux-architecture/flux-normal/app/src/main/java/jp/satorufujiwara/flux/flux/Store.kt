package jp.satorufujiwara.flux.flux

abstract class Store(private val dispatcher: Dispatcher,
                     lifecycleCallback: ActivityLifecycleCallback
) {

  private val callback: (Action<*>) -> Unit = { on(it) }

  init {
    lifecycleCallback.on(
        onCreate = { dispatcher.register(callback) },
        onDestroy = { dispatcher.unregister(callback) }
    )
  }

  abstract fun on(action: Action<*>)
}