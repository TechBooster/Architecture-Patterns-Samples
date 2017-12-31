package jp.satorufujiwara.flux.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.satorufujiwara.flux.di.modules.MainFragmentModule
import jp.satorufujiwara.flux.di.modules.MainModule
import jp.satorufujiwara.flux.ui.main.MainActivity

@Module
internal abstract class UiModule {

  @PerActivityScope
  @ContributesAndroidInjector(modules = [MainModule::class, MainFragmentModule::class])
  internal abstract fun contributeMainActivity(): MainActivity

}