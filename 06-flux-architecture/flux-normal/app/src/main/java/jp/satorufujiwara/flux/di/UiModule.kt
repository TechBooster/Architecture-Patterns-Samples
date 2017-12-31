package jp.satorufujiwara.flux.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import jp.satorufujiwara.flux.di.modules.MainFragmentModule
import jp.satorufujiwara.flux.di.modules.MainModule
import jp.satorufujiwara.flux.flux.ActivityLifecycleCallback
import jp.satorufujiwara.flux.ui.main.MainActivity

@Module
internal abstract class UiModule {

  @PerActivityScope
  @ContributesAndroidInjector(modules = [FluxModule::class, MainModule::class, MainFragmentModule::class])
  internal abstract fun contributeMainActivity(): MainActivity

}

@Module
internal class FluxModule {

  @PerActivityScope
  @Provides
  fun provideActivityLifecycleCallback() = ActivityLifecycleCallback()

}