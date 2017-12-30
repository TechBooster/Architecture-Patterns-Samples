package jp.satorufujiwara.flux.flux

import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Dispatcher @Inject constructor() {

  private val dispatcherProcessor: FlowableProcessor<Action<*>>
      = PublishProcessor.create<Action<*>>()

  fun <T> dispatch(action: Action<T>) {
    dispatcherProcessor.onNext(action)
  }

  fun on(type: String) = dispatcherProcessor
      .filter { it.type == type }

}