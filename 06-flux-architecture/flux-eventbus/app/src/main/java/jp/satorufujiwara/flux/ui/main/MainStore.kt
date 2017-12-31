package jp.satorufujiwara.flux.ui.main

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import jp.satorufujiwara.flux.data.entity.Repo
import jp.satorufujiwara.flux.flux.ActivityLifecycleCallback
import jp.satorufujiwara.flux.flux.Dispatcher
import jp.satorufujiwara.flux.flux.Store
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainStore constructor(dispatcher: Dispatcher,
                            lifecycleCallback: ActivityLifecycleCallback)
  : Store(dispatcher, lifecycleCallback) {

  val repos: ObservableList<Repo> = ObservableArrayList()
  val repoReadmeUrl = ObservableField<String>()

  @Subscribe(threadMode = ThreadMode.MAIN)
  fun on(action: MainAction.RefreshRepo) {
    repos.clear()
    repos.addAll(action.data)
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  fun on(action: MainAction.ShowRepoReadme) {
    repoReadmeUrl.set(action.data)
  }

}