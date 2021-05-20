package com.reactive.crud_card_webflux.service;

import java.util.HashMap;
import java.util.Map;

public class TypeCard {

    public static Map<String, String> createTypes() {
        Map<String, String> types = new HashMap<>();
        types.put("03", "MasterCard");
        types.put("06", "VISA");
        types.put("12", "PRIME");
        return types;
    }

    public static String valType(String code) {
        if (createTypes().get(code) == null) {
            throw new IllegalArgumentException("codigo invalido");
        }
        return createTypes().get(code);

    }
}