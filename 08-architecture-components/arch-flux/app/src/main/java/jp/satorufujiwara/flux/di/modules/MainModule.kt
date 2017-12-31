package jp.satorufujiwara.flux.di.modules

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import jp.satorufujiwara.flux.di.PerActivityScope
import jp.satorufujiwara.flux.di.ViewModelKey
import jp.satorufujiwara.flux.ui.main.MainDispatcher
import jp.satorufujiwara.flux.ui.main.MainStore
import jp.satorufujiwara.flux.ui.main.RepoDetailDialogFragment

@Module
internal abstract class MainModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainStore::class)
  abstract fun bindMainStore(viewModel: MainStore): ViewModel

  @ContributesAndroidInjector
  abstract fun contributeRepoDetailDialogFragment(): RepoDetailDialogFragment
}

@Module
internal class MainDispatcherModule {

  @PerActivityScope
  @Provides
  fun provideMainDispatcher() = MainDispatcher()

}
