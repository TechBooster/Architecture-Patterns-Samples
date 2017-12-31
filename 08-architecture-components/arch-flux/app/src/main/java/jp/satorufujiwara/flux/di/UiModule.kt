package jp.satorufujiwara.flux.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.satorufujiwara.flux.di.modules.MainDispatcherModule
import jp.satorufujiwara.flux.di.modules.MainModule
import jp.satorufujiwara.flux.ui.main.MainActivity

@Module
internal abstract class UiModule {

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): StoreProvider

  @PerActivityScope
  @ContributesAndroidInjector(modules = [MainModule::class, MainDispatcherModule::class])
  internal abstract fun contributeMainActivity(): MainActivity

}