package org.example;

import org.springframework.beans.factory.annotation.Value;

public class Z {
    @Value("${Timezone}")
    private String timezone;

    public String getTimezone() {
        return timezone;
    }
}
