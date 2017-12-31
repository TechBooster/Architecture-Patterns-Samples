package jp.satorufujiwara.mvvm.di

import android.app.Application
import jp.satorufujiwara.mvvm.AppLifecycleCallbacks
import timber.log.Timber

class DebugAppLifecycleCallbacks : AppLifecycleCallbacks {

  override fun onCreate(application: Application) {
    Timber.plant(Timber.DebugTree())
  }

  override fun onTerminate(application: Application) {

  }
}
