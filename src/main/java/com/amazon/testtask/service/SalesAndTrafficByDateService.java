package com.amazon.testtask.service;

import com.amazon.testtask.domain.entity.SalesAndTrafficByDate;

import java.util.List;

public interface SalesAndTrafficByDateService {
    SalesAndTrafficByDate getByDateAndSalesByDateAndTrafficByDate(String date, Object salesByDate, Object trafficByDate);

    List<SalesAndTrafficByDate> getByDate(String date);

    List<SalesAndTrafficByDate> getAll();
}
