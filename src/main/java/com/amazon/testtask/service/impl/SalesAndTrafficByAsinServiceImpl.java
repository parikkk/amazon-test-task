package com.amazon.testtask.service.impl;

import com.amazon.testtask.domain.entity.SalesAndTrafficByAsin;
import com.amazon.testtask.repository.SalesAndTrafficByAsinRepository;
import com.amazon.testtask.service.SalesAndTrafficByAsinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesAndTrafficByAsinServiceImpl implements SalesAndTrafficByAsinService {

    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;

    @Autowired
    public SalesAndTrafficByAsinServiceImpl(SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository) {
        this.salesAndTrafficByAsinRepository = salesAndTrafficByAsinRepository;
    }

    @Override
    public SalesAndTrafficByAsin getByParentAsinAndSalesByAsinAndTrafficByAsin(String parentAsin, Object salesByAsin, Object trafficByAsin) {
        return salesAndTrafficByAsinRepository.findByParentAsinAndSalesByAsinAndTrafficByAsin(parentAsin, salesByAsin, trafficByAsin);
    }

    @Override
    public List<SalesAndTrafficByAsin> getByParentAsin(String parentAsin) {
        return salesAndTrafficByAsinRepository.findByParentAsin(parentAsin);
    }

    @Override
    public List<SalesAndTrafficByAsin> getAll() {
        return salesAndTrafficByAsinRepository.findAll();
    }
}
