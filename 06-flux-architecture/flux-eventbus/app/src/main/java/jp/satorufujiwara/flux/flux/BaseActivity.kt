package jp.satorufujiwara.flux.flux

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

  @Inject lateinit var lifecycleCallback: ActivityLifecycleCallback

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycleCallback.dispatchOnCreate()
  }

  override fun onDestroy() {
    lifecycleCallback.dispatchOnDestroy()
    lifecycleCallback.clear()
    super.onDestroy()
  }
}