package jp.satorufujiwara.flux.flux

abstract class Store(private val dispatcher: Dispatcher,
                     lifecycleCallback: ActivityLifecycleCallback) {

  init {
    lifecycleCallback.on(
        onCreate = { dispatcher.register(this) },
        onDestroy = { dispatcher.unregister(this) }
    )
  }
}