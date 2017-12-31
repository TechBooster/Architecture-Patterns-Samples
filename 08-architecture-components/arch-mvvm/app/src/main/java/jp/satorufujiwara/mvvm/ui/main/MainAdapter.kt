package jp.satorufujiwara.mvvm.ui.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.satorufujiwara.mvvm.R
import jp.satorufujiwara.mvvm.data.entity.Repo
import jp.satorufujiwara.mvvm.databinding.MainRepoItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
  val items = ArrayList<Repo>()
  var onItemClicked: ((item: Repo) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
    return ViewHolder(DataBindingUtil.inflate<MainRepoItemBinding>(
        LayoutInflater.from(parent.context), R.layout.main_repo_item, parent, false))
  }

  override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
    val item = items[position]
    holder.binding.repo = item
    holder.binding.root.setOnClickListener { onItemClicked?.invoke(item) }
    holder.binding.executePendingBindings()
  }

  override fun getItemCount() = items.size

  inner class ViewHolder(val binding: MainRepoItemBinding) : RecyclerView.ViewHolder(binding.root)
}