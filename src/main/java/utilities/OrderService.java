package utilities;

import java.math.BigDecimal;

public class OrderService {

    public BigDecimal calculateFinalPrice(
            BigDecimal price,
            int quantity,
            BigDecimal discountPercentage
    ) {
        if (price == null || discountPercentage == null) {
            throw new IllegalArgumentException("Price and discount must not be null");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0 || quantity <= 0) {
            throw new IllegalArgumentException("Invalid price or quantity");
        }

        if (discountPercentage.compareTo(BigDecimal.ZERO) < 0
                || discountPercentage.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Invalid discount");
        }

        BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));

        BigDecimal discount = total
                .multiply(discountPercentage)
                .divide(BigDecimal.valueOf(100));

        return total.subtract(discount);
    }
}
