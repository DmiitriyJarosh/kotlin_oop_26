import org.example.divideOrZero
import org.example.lazy2
import org.example.AnyConsumer
import org.example.Supplier
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Hw3Test {
    @Test
    fun `Supplier produces values covariantly`() {
        val stringSupplier: Supplier<String> = Supplier { "value" }
        val anySupplier: Supplier<Any> = stringSupplier

        assertEquals("value", anySupplier.get())
    }

    @Test
    fun `AnyConsumer accepts values contravariantly`() {
        var consumed: Any? = null
        val anyConsumer: AnyConsumer<Any> = AnyConsumer { consumed = it }
        val stringConsumer: AnyConsumer<String> = anyConsumer

        stringConsumer.accept("value")

        assertEquals("value", consumed)
    }

    @Test
    fun `divideOrZero returns zero for a zero divisor`() {
        assertEquals(0, divideOrZero(10, 0))
    }

    @Test
    fun `divideOrZero divides normally`() {
        assertEquals(5, divideOrZero(10, 2))
    }

    @Test
    fun `lazy2 initializes only on first access and caches the result`() {
        var initializations = 0
        val value by lazy2 {
            initializations++
            12
        }

        assertEquals(0, initializations)
        assertEquals(12, value)
        assertEquals(12, value)
        assertEquals(1, initializations)
    }

    @Test
    fun `lazy2 caches a null result`() {
        var initializations = 0
        val value: String? by lazy2 {
            initializations++
            null
        }

        assertEquals(null, value)
        assertEquals(null, value)
        assertEquals(1, initializations)
    }
}
