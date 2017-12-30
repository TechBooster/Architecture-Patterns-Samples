package jp.satorufujiwara.flux.di.modules

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import jp.satorufujiwara.flux.di.PerActivityScope
import jp.satorufujiwara.flux.flux.Dispatcher
import jp.satorufujiwara.flux.ui.main.MainStore
import jp.satorufujiwara.flux.ui.main.RepoDetailDialogFragment

@Module
internal class MainModule {

  @PerActivityScope
  @Provides
  fun provideMainStore(dispatcher: Dispatcher) = MainStore(dispatcher)

}

@Module
internal abstract class MainFragmentModule {

  @ContributesAndroidInjector
  abstract fun contributeRepoDetailDialogFragment(): RepoDetailDialogFragment

}