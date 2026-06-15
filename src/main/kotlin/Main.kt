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
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int?,
    val date: Int,
    val text: String?,
    val replyOwnerId: Int?,
    val replyPostId: Int?,
    val friendsOnly: Boolean,
    val copyright: String?,
    val signerId: Int?,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val attachments: Array<Attachment> = emptyArray()
)

data class Photo(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String
)

data class PhotoAttachment(
    val photo: Photo
) : Attachment() {

    override val type: String = "photo"
}

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int
)

data class VideoAttachment(
    val video: Video
) : Attachment() {

    override val type: String = "video"
}

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int
)

data class AudioAttachment(
    val audio: Audio
) : Attachment() {

    override val type: String = "audio"
}

data class Document(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int
)

data class DocumentAttachment(
    val document: Document
) : Attachment() {

    override val type: String = "doc"
}

data class Link(
    val url: String,
    val title: String,
    val caption: String?
)

data class LinkAttachment(
    val link: Link
) : Attachment() {

    override val type: String = "link"
}

object WallService {

    private var posts = emptyArray<Post>()
    private var nextId = 1

    fun add(post: Post): Post {
        val postWithId = post.copy(id = nextId++)
        posts += postWithId
        return postWithId
    }

    fun update(post: Post): Boolean {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == post.id) {

                posts[index] = post.copy(
                    ownerId = currentPost.ownerId,
                    date = currentPost.date
                )

                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        nextId = 1
    }
}

fun main() {

    val photoAttachment = PhotoAttachment(
        Photo(
            id = 1,
            ownerId = 1,
            photo130 = "url130",
            photo604 = "url604"
        )
    )

    val videoAttachment = VideoAttachment(
        Video(
            id = 10,
            ownerId = 1,
            title = "Funny Video",
            duration = 30
        )
    )

    val post = Post(
        id = 0,
        ownerId = 1,
        fromId = 1,
        createdBy = null,
        date = 1711111111,
        text = "Пост с вложениями",
        replyOwnerId = null,
        replyPostId = null,
        friendsOnly = false,
        copyright = null,
        signerId = null,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        markedAsAds = false,
        isFavorite = false,
        attachments = arrayOf(
            photoAttachment,
            videoAttachment
        )
    )

    WallService.add(post)
}