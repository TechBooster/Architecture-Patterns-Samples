package jp.satorufujiwara.flux.di

import dagger.Module
import dagger.Provides
import jp.satorufujiwara.flux.AppLifecycleCallbacks
import jp.satorufujiwara.flux.data.di.DataModule
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class AppModule {

  @Singleton
  @Provides
  fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = ReleaseAppLifecycleCallbacks()

}