package com.amazon.testtask.controller;

import com.amazon.testtask.domain.entity.SalesAndTrafficByDate;
import com.amazon.testtask.service.SalesAndTrafficByDateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salesByDate")
public class SalesAndTrafficByDateController {

    private static final Logger log = LoggerFactory.getLogger(SalesAndTrafficByDateController.class);

    private final SalesAndTrafficByDateService salesAndTrafficByDateService;

    @Autowired
    public SalesAndTrafficByDateController(SalesAndTrafficByDateService salesAndTrafficByDateService) {
        this.salesAndTrafficByDateService = salesAndTrafficByDateService;
    }

    /**
     * Get all SalesAndTrafficByDate
     *
     * @return List<SalesAndTrafficByDate>
     */
    @GetMapping
    @Cacheable("salesAndTrafficByDate")
    public List<SalesAndTrafficByDate> getAll() {
        log.info("Get all SalesAndTrafficByDate");
        return salesAndTrafficByDateService.getAll();
    }

    /**
     * Get list of SalesAndTrafficByDate objects by date
     *
     * @param date
     * @return List<SalesAndTrafficByDate>
     */
    @GetMapping("/{date}")
    @Cacheable("salesAndTrafficByDate")
    public List<SalesAndTrafficByDate> getByDate(@PathVariable String date) {
        log.info("Get list of SalesAndTrafficByDate objects by date : ", date);
        return salesAndTrafficByDateService.getByDate(date);
    }
}
