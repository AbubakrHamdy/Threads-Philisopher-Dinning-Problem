package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.OrderService;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void shouldCalculateFinalPriceWithoutDiscount() {
        BigDecimal result = orderService.calculateFinalPrice(
                BigDecimal.valueOf(100),
                2,
                BigDecimal.ZERO
        );

        assertEquals(BigDecimal.valueOf(200), result);
    }

    @Test
    void shouldCalculateFinalPriceWithDiscount() {
        BigDecimal result = orderService.calculateFinalPrice(
                BigDecimal.valueOf(100),
                2,
                BigDecimal.valueOf(10)
        );

        assertEquals(BigDecimal.valueOf(180), result);
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> orderService.calculateFinalPrice(
                        null,
                        1,
                        BigDecimal.ZERO
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenQuantityIsInvalid() {
        assertThrows(
                IllegalArgumentException.class,
                () -> orderService.calculateFinalPrice(
                        BigDecimal.valueOf(100),
                        0,
                        BigDecimal.ZERO
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenDiscountIsInvalid() {
        assertThrows(
                IllegalArgumentException.class,
                () -> orderService.calculateFinalPrice(
                        BigDecimal.valueOf(100),
                        1,
                        BigDecimal.valueOf(150)
                )
        );
    }
}
