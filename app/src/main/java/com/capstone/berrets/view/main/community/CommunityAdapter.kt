package com.capstone.berrets.view.main.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.berrets.databinding.CardPostBinding
import com.capstone.berrets.model.PostItem

class CommunityAdapter: ListAdapter<PostItem, CommunityAdapter.ViewHolder>(DIFF_CALLBACK) {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		val binding = CardPostBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val post = getItem(position)
		holder.bind(post)
	}

	class ViewHolder(private val binding: CardPostBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(post: PostItem) {
			Glide.with(itemView.context)
				.load(post.profileImage)
				.into(binding.postProfileImage)
			with(binding) {
				name.text = post.name
				userName.text = post.userName
				postTime.text = post.time
				postContent.text = post.content
				likeCount.text = post.like.toString()
				commentCount.text = post.comment.toString()
				bookmarkCount.text = post.bookmark.toString()
			}
		}
	}

	companion object {
		val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PostItem>() {
			override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
				return oldItem == newItem
			}
			override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
				return oldItem == newItem
			}
		}
	}
}