package jp.satorufujiwara.flux.ui.main

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import jp.satorufujiwara.flux.data.entity.Repo
import jp.satorufujiwara.flux.flux.Action
import jp.satorufujiwara.flux.flux.ActivityLifecycleCallback
import jp.satorufujiwara.flux.flux.Dispatcher
import jp.satorufujiwara.flux.flux.Store
import javax.inject.Inject

class MainStore @Inject constructor(dispatcher: Dispatcher,
                                    lifecycleCallback: ActivityLifecycleCallback)
  : Store(dispatcher, lifecycleCallback) {

  val repos: ObservableList<Repo> = ObservableArrayList()
  val repoReadmeUrl = ObservableField<String>()

  override fun on(action: Action<*>) {
    when (action.type) {
      MainAction.RefreshRepo.TYPE -> {
        val data = (action as MainAction.RefreshRepo).data
        repos.clear()
        repos.addAll(data)
      }
      MainAction.ShowRepoReadme.TYPE -> {
        val data = (action as MainAction.ShowRepoReadme).data
        repoReadmeUrl.set(data)
      }
    }
  }

  fun useSmartCast(action: Action<*>) {
    when (action) {
      is MainAction.RefreshRepo -> {
        repos.clear()
        repos.addAll(action.data)
      }
      is MainAction.ShowRepoReadme -> {
        repoReadmeUrl.set(action.data)
      }
    }
  }
}