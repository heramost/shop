package com.hera.shop.utl;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtil {

    public Date now() {
        return new Date();
    }
}
