package jp.satorufujiwara.mvvm.ui.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatDialog
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import jp.satorufujiwara.mvvm.di.Injectable
import jp.satorufujiwara.mvvm.util.ext.observe
import javax.inject.Inject

class RepoDetailDialogFragment : DialogFragment(), Injectable {

  @Inject lateinit var viewModelProvider: ViewModelProvider.Factory
  private lateinit var viewModel: MainViewModel

  companion object {
    private const val ARGS_REPO_OWNER = "repo_owner"
    private const val ARGS_REPO_NAME = "repo_name"
    fun newInstance(repoOwner: String, repoName: String) = RepoDetailDialogFragment().apply {
      arguments = Bundle().apply {
        putString(ARGS_REPO_OWNER, repoOwner)
        putString(ARGS_REPO_NAME, repoName)
      }
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    viewModel = ViewModelProviders.of(this, viewModelProvider).get(MainViewModel::class.java)
    val dialog = WebViewDialog(activity!!)
    viewModel.repoReadmeUrl.observe(this) {
      dialog.webView.loadUrl(it)
    }
    return dialog
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val owner = arguments?.getString(ARGS_REPO_OWNER) ?: return
    val name = arguments?.getString(ARGS_REPO_NAME) ?: return
    viewModel.fetchReadme(owner, name)
  }

}

class WebViewDialog(context: Context) : AppCompatDialog(context) {
  @SuppressLint("SetJavaScriptEnabled")
  val webView = WebView(context).apply {
    webViewClient = WebViewClient()
    webChromeClient = WebChromeClient()
    settings.javaScriptEnabled = true
  }

  init {
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(webView)
    DisplayMetrics().let {
      (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(it)
      window.attributes = window.attributes.apply {
        width = it.widthPixels * 95 / 100
        height = it.heightPixels * 90 / 100
      }
    }
  }
}