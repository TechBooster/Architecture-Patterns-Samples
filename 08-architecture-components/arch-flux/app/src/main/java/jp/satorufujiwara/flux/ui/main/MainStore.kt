package jp.satorufujiwara.flux.ui.main

import android.arch.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.satorufujiwara.flux.data.entity.Repo
import jp.satorufujiwara.flux.flux.Store
import jp.satorufujiwara.flux.util.ext.toLiveData
import javax.inject.Inject

class MainStore @Inject constructor(private val dispatcher: MainDispatcher) : Store() {

  val repos: LiveData<List<Repo>> = dispatcher.onRefreshRepo
      .map { it.data }
      .observeOn(AndroidSchedulers.mainThread())
      .toLiveData()

  val repoReadmeUrl: LiveData<String> = dispatcher.onShowRepoReadme
      .map { it.data }
      .observeOn(AndroidSchedulers.mainThread())
      .toLiveData()

}