package io.bachiri.abderrahman.bamapp.repositories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.bachiri.abderrahman.bamapp.R
import io.bachiri.abderrahman.bamapp.data.Repository
import kotlinx.android.synthetic.main.repo_item.view.*

class RepositoryAdapter(
    private val context: Context,
    private var repositories: List<Repository>
) : RecyclerView.Adapter<RepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.repo_item, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val currentRepository = repositories[position]
        holder.bind(currentRepository)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    fun setData(repositories: List<Repository>) {
        this.repositories = repositories
        notifyDataSetChanged()
    }

}

class RepositoryViewHolder(var viewItem: View) : RecyclerView.ViewHolder(viewItem) {

    fun bind(repository: Repository) {
        viewItem.repoName.text = repository.repoName
        viewItem.repoLang.text = repository.repoLanguage

        //TODO change implementation (Testing purpose)
        repository.repoLanguage?.let {
            when (repository.repoLanguage) {
                "Objective-C" -> {
                    viewItem.repoItem.setBackgroundResource(R.color.objectiveC)
                }
                "C++" -> {
                    viewItem.repoItem.setBackgroundResource(R.color.cpp)
                }
                "Java" -> {
                    viewItem.repoItem.setBackgroundResource(R.color.java)
                }
                "JavaScript" -> {
                    viewItem.repoItem.setBackgroundResource(R.color.js)
                }
                "HTML" -> {
                    viewItem.repoItem.setBackgroundResource(R.color.html)
                }
                "CoffeeScript" -> {
                    viewItem.repoItem.setBackgroundResource(R.color.coffeescript)
                }
                else -> {
                    viewItem.repoItem.setBackgroundResource(R.color.defaultColor)
                }
            }
        }

    }
}