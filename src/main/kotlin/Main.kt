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
    var posts = emptyArray<Post>()

    fun add(post: Post) {
        posts += post
    }

    fun printPosts() {
        for (post in posts) {
            println(post)
        }
    }
}

fun main() {
    val post1 = Post(
        id = 1,
        ownerId = 100,
        fromId = 100,
        date = 1680000000,
        text = "Привет, мир!"
    )

    val post2 = Post(
        id = 2,
        ownerId = 101,
        fromId = 101,
        text = "Мой второй пост",
        likes = Likes(count = 15)
    )

    WallService.add(post1)
    WallService.add(post2)

    WallService.printPosts()
}