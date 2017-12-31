package jp.satorufujiwara.flux.ui.main

import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import jp.satorufujiwara.flux.flux.Dispatcher

class MainDispatcher : Dispatcher() {

  private val dispatcherRefreshRepo: FlowableProcessor<MainAction.RefreshRepo>
      = BehaviorProcessor.create<MainAction.RefreshRepo>().toSerialized()
  val onRefreshRepo: Flowable<MainAction.RefreshRepo> = dispatcherRefreshRepo
  private val dispatcherShowRepoReadme: FlowableProcessor<MainAction.ShowRepoReadme>
      = BehaviorProcessor.create<MainAction.ShowRepoReadme>().toSerialized()
  val onShowRepoReadme: Flowable<MainAction.ShowRepoReadme> = dispatcherShowRepoReadme

  fun dispatch(action: MainAction.RefreshRepo) {
    dispatcherRefreshRepo.onNext(action)
  }

  fun dispatch(action: MainAction.ShowRepoReadme) {
    dispatcherShowRepoReadme.onNext(action)
  }

}
