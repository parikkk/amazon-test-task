package com.amazon.testtask.service;

import com.amazon.testtask.domain.entity.SalesAndTrafficByAsin;

import java.util.List;

public interface SalesAndTrafficByAsinService {

    SalesAndTrafficByAsin getByParentAsinAndSalesByAsinAndTrafficByAsin(String parentAsin, Object salesByAsin, Object trafficByAsin);

    List<SalesAndTrafficByAsin> getByParentAsin(String parentAsin);

    List<SalesAndTrafficByAsin> getAll();
}
