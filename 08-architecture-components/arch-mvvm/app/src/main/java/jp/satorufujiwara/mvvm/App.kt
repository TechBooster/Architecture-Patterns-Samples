package jp.satorufujiwara.mvvm

import dagger.android.support.DaggerApplication
import jp.satorufujiwara.mvvm.di.DaggerAppComponent
import jp.satorufujiwara.mvvm.di.applyAutoInjector
import javax.inject.Inject

class App : DaggerApplication() {

  @Inject lateinit var appLifecycleCallbacks: AppLifecycleCallbacks

  override fun applicationInjector() = DaggerAppComponent.builder()
      .application(this)
      .build()

  override fun onCreate() {
    super.onCreate()
    applyAutoInjector()
    appLifecycleCallbacks.onCreate(this)
  }

  override fun onTerminate() {
    appLifecycleCallbacks.onTerminate(this)
    super.onTerminate()
  }

}