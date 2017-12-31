package jp.satorufujiwara.flux.ui.main

import android.support.v4.app.FragmentActivity
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.satorufujiwara.flux.data.repository.GitHubRepository
import jp.satorufujiwara.flux.di.PerActivityScope
import timber.log.Timber
import javax.inject.Inject

@PerActivityScope
class MainActionCreator @Inject constructor(
    private val dispatcher: MainDispatcher,
    private val repository: GitHubRepository
) {

  fun fetchRepo(repoOwner: String)
      = repository.fetchUserRepos(repoOwner)
      .subscribeOn(Schedulers.io())
      .subscribeBy(
          onSuccess = {
            dispatcher.dispatch(MainAction.RefreshRepo(it))
          },
          onError = {
            Timber.e(it)
          })

  fun fetchReadme(repoOwner: String, repoName: String)
      = repository.fetchReadme(repoOwner, repoName)
      .subscribeOn(Schedulers.io())
      .subscribeBy(
          onSuccess = {
            dispatcher.dispatch(MainAction.ShowRepoReadme(it.html_url))
          },
          onError = {
            Timber.e(it)
          }
      )

  fun showRepoDetailDialog(activity: FragmentActivity, repoOwner: String, repoName: String) {
    RepoDetailDialogFragment.newInstance(repoOwner, repoName)
        .show(activity.supportFragmentManager, "MainAction.RepoDetailDialog")
  }
}

