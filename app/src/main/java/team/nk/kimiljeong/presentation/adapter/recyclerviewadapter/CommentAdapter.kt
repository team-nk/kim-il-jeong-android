package team.nk.kimiljeong.presentation.adapter.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.nk.kimiljeong.data.model.remote.common.CommentInformation
import team.nk.kimiljeong.databinding.ItemCommentBinding

class CommentAdapter(
    private val comments: List<CommentInformation>,
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentInformation) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false,
            ),
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}
