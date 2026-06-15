data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = true
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val markedAsAds: Boolean = false,
    val favorite: Boolean = false,
    val comments: Comments = Comments(),
    val likes: Likes = Likes()
)

object WallService {

    private var posts = emptyArray<Post>()
    private var nextId = 1

    fun clear() {
        posts = emptyArray()
        nextId = 1
    }

    fun add(post: Post): Post {
        val postWithId = post.copy(id = nextId++)
        posts += postWithId
        return postWithId
    }

    fun update(post: Post): Boolean {
        for (index in posts.indices) {
            if (posts[index].id == post.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }
}

fun main() {

    val firstPost = WallService.add(
        Post(
            ownerId = 1,
            text = "Первый пост"
        )
    )

    val secondPost = WallService.add(
        Post(
            ownerId = 2,
            text = "Второй пост"
        )
    )

    println(firstPost)
    println(secondPost)

    val updated = Post(
        id = firstPost.id,
        ownerId = 1,
        text = "Первый пост обновлен"
    )

    println(WallService.update(updated)) // true

    println(
        WallService.update(
            Post(
                id = 999,
                text = "Такого поста нет"
            )
        )
    ) // false
}