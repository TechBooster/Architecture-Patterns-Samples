package jp.satorufujiwara.flux.ui.main

import jp.satorufujiwara.flux.data.entity.Repo
import jp.satorufujiwara.flux.flux.Action

sealed class MainAction<out T>(override val type: String) : Action<T> {
  class RefreshRepo(override val data: List<Repo>)
    : MainAction<List<Repo>>(TYPE) {
    companion object {
      const val TYPE = "MainAction.RefreshRepo"
    }
  }

  class ShowRepoReadme(override val data: String)
    : MainAction<String>(TYPE) {
    companion object {
      const val TYPE = "MainAction.ShowRepoReadme"
    }
  }
}