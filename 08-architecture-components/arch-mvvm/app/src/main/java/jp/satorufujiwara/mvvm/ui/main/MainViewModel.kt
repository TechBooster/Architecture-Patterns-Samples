package jp.satorufujiwara.mvvm.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.satorufujiwara.mvvm.data.entity.Repo
import jp.satorufujiwara.mvvm.data.repository.GitHubRepository
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: GitHubRepository) : ViewModel() {

  private val _repos = MutableLiveData<List<Repo>>()
  private val _repoReadmeUrl = MutableLiveData<String>()
  val repos: LiveData<List<Repo>> = _repos
  val repoReadmeUrl: LiveData<String> = _repoReadmeUrl

  fun fetchRepo(repoOwner: String)
      = repository.fetchUserRepos(repoOwner)
      .subscribeOn(Schedulers.io())
      .subscribeBy(
          onSuccess = {
            _repos.postValue(it)
          },
          onError = {
            Timber.e(it)
          })

  fun fetchReadme(repoOwner: String, repoName: String)
      = repository.fetchReadme(repoOwner, repoName)
      .subscribeOn(Schedulers.io())
      .subscribeBy(
          onSuccess = {
            _repoReadmeUrl.postValue(it.html_url)
          },
          onError = {
            Timber.e(it)
          }
      )

}