package com.amazon.testtask.controller;

import com.amazon.testtask.domain.entity.SalesAndTrafficByAsin;
import com.amazon.testtask.domain.entity.SalesAndTrafficByDate;
import com.amazon.testtask.service.SalesAndTrafficByAsinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/salesByAsin")
public class SalesAndTrafficByAsinController {

    private static final Logger log = LoggerFactory.getLogger(SalesAndTrafficByAsinController.class);

    private SalesAndTrafficByAsinService salesAndTrafficByAsinService;

    @Autowired
    public SalesAndTrafficByAsinController(SalesAndTrafficByAsinService salesAndTrafficByAsinService) {
        this.salesAndTrafficByAsinService = salesAndTrafficByAsinService;
    }

    /**
     * Get all SalesAndTrafficByAsin objects
     *
     * @return List<SalesAndTrafficByAsin>
     */
    @GetMapping
    @Cacheable("salesAndTrafficByAsin")
    public List<SalesAndTrafficByAsin> getAll() {
        log.info("Get all SalesAndTrafficByAsin");
        return salesAndTrafficByAsinService.getAll();
    }

    /**
     * Get list of SalesAndTrafficByAsin objects by 'parentAsin'
     *
     * @param parentAsin
     * @return List<SalesAndTrafficByAsin>
     */
    @GetMapping("/{parentAsin}")
    @Cacheable("salesAndTrafficByAsin")
    public List<SalesAndTrafficByAsin>  getByDate(@PathVariable String parentAsin) {
        log.info("Get list of SalesAndTrafficByAsin objects by parentAsin : ", parentAsin);
        return salesAndTrafficByAsinService.getByParentAsin(parentAsin);
    }
}
