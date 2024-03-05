package com.amazon.testtask.service.impl;

import com.amazon.testtask.domain.entity.SalesAndTrafficByDate;
import com.amazon.testtask.repository.SalesAndTrafficByDateRepository;
import com.amazon.testtask.service.SalesAndTrafficByDateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesAndTrafficByDateServiceImpl implements SalesAndTrafficByDateService {

    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;

    public SalesAndTrafficByDateServiceImpl(SalesAndTrafficByDateRepository salesAndTrafficByDateRepository) {
        this.salesAndTrafficByDateRepository = salesAndTrafficByDateRepository;
    }

    @Override
    public SalesAndTrafficByDate getByDateAndSalesByDateAndTrafficByDate(String date, Object salesByDate, Object trafficByDate) {
        return salesAndTrafficByDateRepository.findByDateAndSalesByDateAndTrafficByDate(date, salesByDate, trafficByDate);
    }

    @Override
    public List<SalesAndTrafficByDate> getByDate(String date) {
        return salesAndTrafficByDateRepository.findByDate(date);
    }

    @Override
    public List<SalesAndTrafficByDate> getAll() {
        return salesAndTrafficByDateRepository.findAll();
    }
}
