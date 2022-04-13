package com.example.coincombo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
class CoinComboConverterImpl implements CoinComboConverter {

    @Override
    public CoinCombo convert(float dollarAmount) {

        Map<Coin, Integer> coinsCount = new HashMap<>();
        int remainingCents = (int) (dollarAmount * 100); // Ignoring rounding up
        for (final Coin coin : Coin.values()) {
            final int coinCount = remainingCents / coin.getValueInCents();
            coinsCount.put(coin, coinCount);
            remainingCents = remainingCents % coin.getValueInCents();
        }

        final CoinCombo combo = new CoinCombo();
        combo.setSilverDollar(coinsCount.get(Coin.SILVER_DOLLAR));
        combo.setHalfDollar(coinsCount.get(Coin.HALF_DOLLAR));
        combo.setQuarter(coinsCount.get(Coin.QUARTER));
        combo.setDime(coinsCount.get(Coin.DIME));
        combo.setNickel(coinsCount.get(Coin.NICKEL));
        combo.setPenny(coinsCount.get(Coin.PENNY));
        return combo;
    }

    // Defining this enum inside the class as it's just an implementation detail to convert.
    // Other classes don't need to know about this enum.
    enum Coin {
        SILVER_DOLLAR(100),
        HALF_DOLLAR(50),
        QUARTER(25),
        DIME(10),
        NICKEL(5),
        PENNY(1);

        private final int valueInCents;

        Coin(int valueInCents) {
            this.valueInCents = valueInCents;
        }

        public int getValueInCents() {
            return valueInCents;
        }
    }
}
