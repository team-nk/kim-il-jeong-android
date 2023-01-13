package team.nk.kimiljeong.presentation.adapter.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.nk.kimiljeong.data.model.remote.common.PostInformation
import team.nk.kimiljeong.databinding.ItemPostBinding
import team.nk.kimiljeong.presentation.util.parseColorToResource
import team.nk.kimiljeong.presentation.view.post.ItemClickListener
import team.nk.kimiljeong.presentation.view.post.selectedPostId
import team.nk.kimiljeong.presentation.view.postinspect.selectedPostInformation

class PostAdapter(
    var posts: List<PostInformation>,
    private val onItemClick: ItemClickListener,
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
                tvItemPostAccountId.text = post.accountId
                tvItemPostAddress.text = post.address
                tvItemPostDate.text = post.timeCreated
                tvItemPostScheduleContent.text = post.scheduleContent
                indicatorItemPostColor.setBackgroundResource(
                    parseColorToResource(post.color)
                )
                root.setOnClickListener {
                    onItemClick.onItemClick()
                    selectedPostId = post.id
                    selectedPostInformation = post
                }
            }
        }
    }
}
