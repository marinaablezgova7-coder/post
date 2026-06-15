import org.junit.Before
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val post = Post(
            ownerId = 1,
            text = "Тестовый пост"
        )

        val result = WallService.add(post)

        assertTrue(result.id != 0)
    }

    @Test
    fun updateExisting() {
        val post1 = WallService.add(
            Post(
                ownerId = 1,
                text = "Первый"
            )
        )

        WallService.add(
            Post(
                ownerId = 2,
                text = "Второй"
            )
        )

        val update = Post(
            id = post1.id,
            ownerId = 10,
            text = "Обновленный пост"
        )

        val result = WallService.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNotExisting() {
        WallService.add(
            Post(
                ownerId = 1,
                text = "Первый"
            )
        )

        WallService.add(
            Post(
                ownerId = 2,
                text = "Второй"
            )
        )

        val update = Post(
            id = 999,
            ownerId = 10,
            text = "Не существует"
        )

        val result = WallService.update(update)

        assertFalse(result)
    }
}