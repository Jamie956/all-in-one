package com.jamie.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String providerOK(Integer id) {
        return "ProviderOK fall back";
    }

    @Override
    public String providerNoTimeOut(Integer id) {
        return null;
    }

    @Override
    public String providerTimeOut(Integer id) {
        return null;
    }

    @Override
    public String providerException(Integer id) {
        return null;
    }
}
