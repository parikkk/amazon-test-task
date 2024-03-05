package com.amazon.testtask.repository;

import com.amazon.testtask.domain.entity.SalesAndTrafficByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SalesAndTrafficByAsinRepository extends MongoRepository<SalesAndTrafficByAsin, Long> {

    SalesAndTrafficByAsin findByParentAsinAndSalesByAsinAndTrafficByAsin(String parentAsin, Object salesByAsin, Object trafficByAsin);

    List<SalesAndTrafficByAsin> findByParentAsin(String parentAsin);
}
