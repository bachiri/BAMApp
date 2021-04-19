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
) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.repo_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
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

class BookViewHolder(var viewItem: View) : RecyclerView.ViewHolder(viewItem) {

    fun bind(repository: Repository) {
        viewItem.repoName.text = repository.repoName

        //TODO change implementation (Testing purpose)
        repository.repoLanguage?.let {
            when (repository.repoLanguage) {
                "Java" -> {
                    viewItem.repoTag.setBackgroundResource(R.color.purple_200)
                }
                "JavaScript" -> {
                    viewItem.repoTag.setBackgroundResource(R.color.design_default_color_secondary)
                }
                else -> {
                    viewItem.repoTag.setBackgroundResource(R.color.design_default_color_error)
                }
            }
        }

    }
}