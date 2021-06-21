package com.xusm.forum.config.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "xusm")
public class CorsProperties {

    private List<String> origin = new ArrayList<>();


    public List<String> getOrigin() {
        return origin;
    }

    public void setOrigin(List<String> origin) {
        this.origin = origin;
    }
}
