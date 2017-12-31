package jp.satorufujiwara.flux.ui.main

import jp.satorufujiwara.flux.data.entity.Repo
import jp.satorufujiwara.flux.flux.Action

sealed class MainAction<out T> : Action<T> {
  class RefreshRepo(override val data: List<Repo>) : MainAction<List<Repo>>()
  class ShowRepoReadme(override val data: String) : MainAction<String>()
}