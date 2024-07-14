package org.example;

import org.springframework.beans.factory.annotation.Autowired;

public class HaloService {
    @Autowired
    private HaloProperties haloProperties;

    public HaloProperties getHaloProperties() {
        return haloProperties;
    }

    public void setHaloProperties(HaloProperties haloProperties) {
        this.haloProperties = haloProperties;
    }
}
