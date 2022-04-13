package com.example.coincombo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinComboController {

    Logger logger = LoggerFactory.getLogger(CoinComboController.class);

    private final CoinComboConverter coinComboConverter;

    public CoinComboController(CoinComboConverter coinComboConverter) {
        this.coinComboConverter = coinComboConverter;
    }

    @GetMapping(path = "/coin_combo", produces = MediaType.APPLICATION_JSON_VALUE)
    public CoinCombo getCoinCombo(@RequestParam String dollarAmount) {
        logger.debug("Converting" + dollarAmount + "to coin combo", dollarAmount);
        return coinComboConverter.convert(Float.parseFloat(dollarAmount));
    }
}
