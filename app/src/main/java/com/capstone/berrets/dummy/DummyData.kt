package com.capstone.berrets.dummy

import com.capstone.berrets.model.PostItem

object DummyData {
	fun generateDummyPost(): List<PostItem> {
		val posts = ArrayList<PostItem>()
		for (i in 0.. 10) {
			val post = PostItem(
				id = i,
				name = "name $i",
				userName = "userName $i",
				time = "time $i",
				content = "content $i",
				like = i,
				comment = i,
				bookmark = i,
				profileImage = "https://www.escapistmagazine.com/wp-content/uploads/2024/04/Gojo-nah-id-win-Jujutsu-Kaisen.jpg"
			)
			posts.add(post)
		}
		return posts
	}
}