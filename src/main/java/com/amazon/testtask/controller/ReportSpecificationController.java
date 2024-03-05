package com.amazon.testtask.controller;

import com.amazon.testtask.domain.entity.ReportSpecification;
import com.amazon.testtask.service.ReportSpecificationService;
import com.amazon.testtask.service.impl.FileServiceImpl;
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
@RequestMapping("/reports")
public class ReportSpecificationController {

    private static final Logger log = LoggerFactory.getLogger(ReportSpecificationController.class);

    private final ReportSpecificationService specificationService;

    @Autowired
    public ReportSpecificationController(ReportSpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    /**
     * Get report by startTime
     *
     * @param startTime
     * @return List<ReportSpecification>
     */
    @GetMapping("/{startTime}")
    @Cacheable("reportSpecification")
    public List<ReportSpecification> getReportsByDataStartTime(@PathVariable String startTime) {
        log.info("Get report by startTime : ", startTime);
        return specificationService.getByDataStartTime(startTime);
    }

    /**
     * Get all reports
     *
     * @return List<ReportSpecification>
     */
    @GetMapping
    @Cacheable("reportSpecification")
    public List<ReportSpecification> getAllReports() {
        log.info("getAllReports");
        return specificationService.getAllReports();
    }
}
