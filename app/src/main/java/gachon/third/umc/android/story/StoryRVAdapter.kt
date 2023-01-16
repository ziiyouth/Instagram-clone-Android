package gachon.third.umc.android.story

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.ItemStoryBinding

class StoryRVAdapter(private var StoryList: ArrayList<StoryData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(v: View, data: StoryData, pos: Int)
    }

    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder1(private val viewBinding: ItemStoryBinding): RecyclerView.ViewHolder(viewBinding.root){

        fun bind(story: StoryData){
            viewBinding.itemStoryId.text = story.id
            viewBinding.itemStoryProfile.setImageResource(story.profileImg)
            viewBinding.itemStoryAdd.setImageResource(R.drawable.ic_story_add)
        }
    }

    inner class ViewHolder2(private val viewBinding: ItemStoryBinding): RecyclerView.ViewHolder(viewBinding.root){

        fun bind(story: StoryData){
            viewBinding.itemStoryId.text = story.id
            viewBinding.itemStoryProfile.setImageResource(story.profileImg)
            viewBinding.itemStoryBorder.setImageResource(R.drawable.ic_story_border)
            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, story, pos)
                }
            }
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemStoryBinding = ItemStoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        if(viewType == 0){
            return ViewHolder1(binding)
        }
        return ViewHolder2(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(StoryList[position].status){
            0 -> {
                (holder as ViewHolder1).bind(StoryList[position])
            }
            1 -> {
                (holder as ViewHolder2).bind(StoryList[position])
            }
        }
    }

    override fun getItemCount(): Int = StoryList.size

    override fun getItemViewType(position: Int): Int {
        return StoryList[position].status
    }




}