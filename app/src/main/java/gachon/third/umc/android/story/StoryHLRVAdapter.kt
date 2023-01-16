package gachon.third.umc.android.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.ItemStoryHighlightBinding
import gachon.third.umc.android.profile.StoryHighlightData

class StoryHLRVAdapter(private var StoryHighlightList: ArrayList<StoryHighlightData>) : RecyclerView.Adapter<StoryHLRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewBinding: ItemStoryHighlightBinding): RecyclerView.ViewHolder(viewBinding.root){

        fun bind(story: StoryHighlightData){


            if (story.status == 0) {
                viewBinding.itemStoryHlBt.setImageResource(R.drawable.profile_story_add_bt)
                viewBinding.itemStoryHlPlus.setImageResource(R.drawable.ic_plus_2)
                viewBinding.itemStoryHlTv.text = "신규"
            }
            else if (story.status == 1)
                viewBinding.itemStoryHlBt.setImageResource(R.drawable.profile_story_highlight)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemStoryHighlightBinding = ItemStoryHighlightBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(StoryHighlightList[position])
    }

    override fun getItemCount(): Int = StoryHighlightList.size
}