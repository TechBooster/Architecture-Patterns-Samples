package jp.satorufujiwara.flux.flux

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseActivity : AppCompatActivity() {

  private val disposable = CompositeDisposable()

  fun Disposable.disposeWhenDestroy() = disposable.add(this)

  override fun onDestroy() {
    disposable.clear()
    super.onDestroy()
  }
}