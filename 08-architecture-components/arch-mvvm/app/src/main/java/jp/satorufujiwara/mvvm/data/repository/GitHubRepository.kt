package jp.satorufujiwara.mvvm.data.repository

import jp.satorufujiwara.mvvm.data.api.GitHubService
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val gitHubService: GitHubService) {

  fun fetchUserRepos(userName: String) = gitHubService.getUserRepos(userName)

  fun fetchReadme(userName: String, repoName: String) = gitHubService.getReadme(userName, repoName)

}
