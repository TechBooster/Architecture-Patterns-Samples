package jp.satorufujiwara.flux.flux

import jp.satorufujiwara.flux.BuildConfig
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbusperf.AppEventBusIndex
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Dispatcher @Inject constructor() {

  private val bus = EventBus.builder()
      .addIndex(AppEventBusIndex())
      .throwSubscriberException(BuildConfig.DEBUG)
      .build()

  fun <T> dispatch(action: Action<T>) {
    bus.post(action)
  }

  fun register(observer: Any) {
    bus.register(observer)
  }

  fun unregister(observer: Any) {
    bus.unregister(observer)
  }

}