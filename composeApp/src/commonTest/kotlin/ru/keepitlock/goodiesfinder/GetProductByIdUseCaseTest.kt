package ru.keepitlock.goodiesfinder

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import ru.keepitlock.goodiesfinder.data.model.Product
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductByIdUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetProductByIdUseCaseTest {

    private val product1 = Product(id = "1", name = "Молоко", latitude = 55.0, longitude = 37.0)
    private val product2 = Product(id = "2", name = "Хлеб", latitude = 56.0, longitude = 38.0)

    private val testRepository = TestProductRepository()
    private val useCase = GetProductByIdUseCase(testRepository)

    @Test
    fun `invoke returns correct product when id exists`() = runTest {
        // Arrange
        testRepository.setProducts(listOf(product1, product2))

        // Act
        val result = useCase("1").first()

        assertEquals(product1, result)
        assertEquals("Молоко", result?.name)
    }

    @Test
    fun `invoke returns null when id does not exist`() = runTest {
        // Arrange: ищем несуществующий ID
        testRepository.setProducts(listOf(product1, product2))

        // Act
        val result = useCase("999").first()

        // Assert
        assertNull(result)
    }

    @Test
    fun `invoke returns null when products list is empty`() = runTest {
        // Arrange: Очищаем список в моке
        testRepository.setProducts(emptyList())

        // Act
        val result = useCase("1").first()

        // Assert
        assertNull(result)
    }

    @Test
    fun `invoke reacts to data changes in repository`() = runTest {
        // Arrange: Сначала пустой список
        testRepository.setProducts(emptyList())

        val flow = useCase("1")

        // Проверяем, что сначала null
        assertNull(flow.first())

        // Act: Меняем данные в репозитории "на лету"
        testRepository.setProducts(listOf(product1))

        // Assert: Flow должен эмитить обновленное значение (так как это StateFlow)
        // Берем второе значение из потока (первое было null)
        // Примечание: в реальном тесте лучше использовать collect с таймаутом или тестовым диспатчером,
        // но для простого примера с StateFlow это сработает, если подписка активна.
        // Однако, так как мы вызываем .first() заново, мы получим актуальное состояние.
        val updatedResult = flow.first()

        assertEquals(product1, updatedResult)
    }

}