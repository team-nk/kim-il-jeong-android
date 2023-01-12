package team.nk.kimiljeong.presentation.adapter.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.nk.kimiljeong.data.model.remote.common.PostInformation
import team.nk.kimiljeong.databinding.ItemPostBinding

class PostAdapter(
    var posts: List<PostInformation>,
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false,
            ),
        )
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(post: PostInformation) {
            binding.run {
                tvItemPostTitle.text = post.title
            }
        }
    }
}
