package jp.satorufujiwara.flux.ui.main

import android.annotation.SuppressLint
import android.app.Dialog
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
import jp.satorufujiwara.flux.di.Injectable
import jp.satorufujiwara.flux.util.OnFieldChangedCallback
import javax.inject.Inject

class RepoDetailDialogFragment : DialogFragment(), Injectable {

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

  @Inject lateinit var actionCreator: MainActionCreator
  @Inject lateinit var store: MainStore

  private val fieldChangedCallback = OnFieldChangedCallback<String> {
    (dialog as? WebViewDialog)?.webView?.loadUrl(it)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = WebViewDialog(activity!!)

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    store.repoReadmeUrl.addOnPropertyChangedCallback(fieldChangedCallback)
    val owner = arguments?.getString(ARGS_REPO_OWNER) ?: return
    val name = arguments?.getString(ARGS_REPO_NAME) ?: return
    actionCreator.fetchReadme(owner, name)
  }

  override fun onDestroy() {
    store.repoReadmeUrl.removeOnPropertyChangedCallback(fieldChangedCallback)
    super.onDestroy()
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