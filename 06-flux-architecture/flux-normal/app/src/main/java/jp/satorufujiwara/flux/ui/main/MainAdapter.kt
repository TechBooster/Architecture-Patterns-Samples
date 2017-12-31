package jp.satorufujiwara.flux.ui.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.MembersInjector
import jp.satorufujiwara.flux.R
import jp.satorufujiwara.flux.data.entity.Repo
import jp.satorufujiwara.flux.databinding.MainRepoItemBinding
import jp.satorufujiwara.flux.flux.ActivityLifecycleCallback
import jp.satorufujiwara.flux.util.ext.createOnListChangedCallback
import javax.inject.Inject

class MainAdapter() : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

  @Inject lateinit var store: MainStore
  @Inject lateinit var activityLifecycleCallback: ActivityLifecycleCallback
  var onItemClicked: ((item: Repo) -> Unit)? = null
  private val listChangedCallback = createOnListChangedCallback()

  fun injectMembersBy(injector: MembersInjector<MainAdapter>) {
    injector.injectMembers(this)
    activityLifecycleCallback.on(
        onCreate = { store.repos.addOnListChangedCallback(listChangedCallback) },
        onDestroy = { store.repos.removeOnListChangedCallback(listChangedCallback) }
    )
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
    return ViewHolder(DataBindingUtil.inflate<MainRepoItemBinding>(
        LayoutInflater.from(parent.context), R.layout.main_repo_item, parent, false))
  }

  override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
    val item = store.repos[position]
    holder.binding.repo = item
    holder.binding.root.setOnClickListener { onItemClicked?.invoke(item) }
    holder.binding.executePendingBindings()
  }

  override fun getItemCount() = store.repos.size

  inner class ViewHolder(val binding: MainRepoItemBinding) : RecyclerView.ViewHolder(binding.root)
}
