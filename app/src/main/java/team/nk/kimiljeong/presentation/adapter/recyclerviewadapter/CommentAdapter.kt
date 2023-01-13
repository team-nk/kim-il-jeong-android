package team.nk.kimiljeong.presentation.adapter.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.nk.kimiljeong.data.model.remote.common.CommentInformation
import team.nk.kimiljeong.databinding.ItemCommentBinding
import team.nk.kimiljeong.presentation.adapter.bindingadapter.loadImageFrom

class CommentAdapter(
    private val comments: List<CommentInformation>,
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentInformation) {
            with(binding) {
                tvItemCommentAccountId.text = comment.accountId
                tvItremCommentContent.text = comment.content
                tvItemCommentTimeCreated.text = comment.timeCreated
                imageItemCommentProfile.loadImageFrom(
                    comment.profileUrl ?: "https://avatars.githubusercontent.com/u/101160207?v=4",
                )
            }
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
