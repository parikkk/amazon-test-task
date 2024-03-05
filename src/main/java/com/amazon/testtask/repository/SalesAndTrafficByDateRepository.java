package com.amazon.testtask.repository;

import com.amazon.testtask.domain.entity.SalesAndTrafficByDate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SalesAndTrafficByDateRepository extends MongoRepository<SalesAndTrafficByDate, Long> {

    SalesAndTrafficByDate findByDateAndSalesByDateAndTrafficByDate(String date, Object salesByDate, Object trafficByDate);

    List<SalesAndTrafficByDate> findByDate(String date);
}
