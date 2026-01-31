/**
 * VULNERABLE CLASS: CurrencyUnit
 * Guidelines:
 * - MUTABLE-1 (6-1): Mutable value type for currency.
 */
public class CurrencyUnit {
    public String symbol;

    public CurrencyUnit(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
