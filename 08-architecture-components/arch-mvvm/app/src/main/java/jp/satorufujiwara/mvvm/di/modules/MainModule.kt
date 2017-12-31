package jp.satorufujiwara.mvvm.di.modules

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import jp.satorufujiwara.mvvm.di.ViewModelKey
import jp.satorufujiwara.mvvm.ui.main.MainViewModel
import jp.satorufujiwara.mvvm.ui.main.RepoDetailDialogFragment

@Module
internal abstract class MainModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun bindMainStore(viewModel: MainViewModel): ViewModel

  @ContributesAndroidInjector
  abstract fun contributeRepoDetailDialogFragment(): RepoDetailDialogFragment
}
