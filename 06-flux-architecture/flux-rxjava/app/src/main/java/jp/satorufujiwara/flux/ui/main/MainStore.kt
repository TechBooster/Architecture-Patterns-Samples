package jp.satorufujiwara.flux.ui.main

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import jp.satorufujiwara.flux.data.entity.Repo
import jp.satorufujiwara.flux.flux.Dispatcher
import jp.satorufujiwara.flux.flux.Store
import javax.inject.Inject

class MainStore @Inject constructor(private val dispatcher: Dispatcher) : Store() {

  private val _repos = BehaviorProcessor.create<List<Repo>>()
  private val _repoReadmeUrl = BehaviorProcessor.create<String>()
  val repos: Flowable<List<Repo>> = _repos
  val repoReadmeUrl: Flowable<String> = _repoReadmeUrl

  init {
    dispatcher.on(MainAction.RefreshRepo.TYPE)
        .map { (it as MainAction.RefreshRepo).data }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(_repos)
    dispatcher.on(MainAction.ShowRepoReadme.TYPE)
        .map { (it as MainAction.ShowRepoReadme).data }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(_repoReadmeUrl)
  }

}