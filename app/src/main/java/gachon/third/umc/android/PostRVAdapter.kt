package gachon.third.umc.android

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.databinding.ItemPostBinding
import gachon.third.umc.android.databinding.ItemStoryBinding

class PostRVAdapter (private var PostList: ArrayList<PostData>) : RecyclerView.Adapter<PostRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewBinding: ItemPostBinding): RecyclerView.ViewHolder(viewBinding.root){

        fun bind(post: PostData){
            viewBinding.itemPostId.text = post.id
            viewBinding.itemPostProfile.setImageResource(post.profileImg)
            viewBinding.itemPostImg.setImageResource(post.postImg)
            viewBinding.itemPostLikenum.text = "좋아요 " + post.postLikeNum + "개"
            viewBinding.itemPostContent.text = SpannableBold(post.postContent, post.id.length)
            viewBinding.itemPostCommentNum.text = "댓글 " + post.postComment + "개 모두 보기"
            viewBinding.itemPostDate.text = post.postDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent,  false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(PostList[position])
    }

    override fun getItemCount(): Int = PostList.size

    private fun SpannableBold(content : String, size : Int): CharSequence? {

        val textData: String = content.toString()
        val builder = SpannableStringBuilder(textData)

        val boldSpan = StyleSpan(Typeface.BOLD)
        builder.setSpan(boldSpan, 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        return builder
    }
}