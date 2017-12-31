package jp.satorufujiwara.mvvm.di

import dagger.Module
import dagger.Provides
import jp.satorufujiwara.mvvm.AppLifecycleCallbacks
import jp.satorufujiwara.mvvm.data.di.DataModule
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class AppModule {

  @Singleton
  @Provides
  fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = DebugAppLifecycleCallbacks()

}