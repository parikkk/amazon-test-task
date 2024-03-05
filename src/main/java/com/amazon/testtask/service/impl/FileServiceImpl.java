package com.amazon.testtask.service.impl;

import com.amazon.testtask.domain.entity.ReportSpecification;
import com.amazon.testtask.domain.entity.SalesAndTrafficByAsin;
import com.amazon.testtask.domain.entity.SalesAndTrafficByDate;
import com.amazon.testtask.service.FileService;
import com.amazon.testtask.service.ReportSpecificationService;
import com.amazon.testtask.service.SalesAndTrafficByAsinService;
import com.amazon.testtask.service.SalesAndTrafficByDateService;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


@Service
public class FileServiceImpl implements FileService {

    static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    public static final String SALES_AND_TRAFFIC_BY_ASIN_COLLECTION_NAME = "salesAndTrafficByAsin";
    public static final String SALES_AND_TRAFFIC_BY_DATE_COLLECTION_NAME = "salesAndTrafficByDate";
    private final static String REPORT_COLLECTION_NAME = "reportSpecification";
    private final MongoTemplate mongo;

    private final ReportSpecificationService specificationService;

    private final SalesAndTrafficByAsinService salesAndTrafficByAsinService;

    private final SalesAndTrafficByDateService salesAndTrafficByDateService;

    @Value("${mongo.data.file.path}")
    private String filePath;

    private Long currentFileSize = 0L;

    @Autowired
    public FileServiceImpl(MongoTemplate mongo, ReportSpecificationService specificationService, SalesAndTrafficByAsinService salesAndTrafficByAsinService, SalesAndTrafficByDateService salesAndTrafficByDateService) {
        this.mongo = mongo;
        this.specificationService = specificationService;
        this.salesAndTrafficByAsinService = salesAndTrafficByAsinService;
        this.salesAndTrafficByDateService = salesAndTrafficByDateService;
    }

    public void initDBFromFile() {
        log.info("Start initialization DataBase from file");
        if (!mongo.collectionExists(REPORT_COLLECTION_NAME)) {
            Object jsonData = parseFromJson();
            saveDataToDB((JSONObject) jsonData);
        }
        File file = new File(filePath);
        currentFileSize = file.length();
        log.info("Finish initialization DataBase from file");
    }

    @CacheEvict(value = {"reportSpecification", "salesAndTrafficByAsin", "salesAndTrafficByDate"}, allEntries = true)
    public void updateDbFromFile() {
        File file = new File(filePath);
        if (currentFileSize != file.length()) {
            Object jsonData = parseFromJson();
            Gson gson = new Gson();
            ((JSONObject) jsonData).forEach((collectionName, jsonObject1) -> {
                switch (collectionName.toString()) {
                    case REPORT_COLLECTION_NAME:
                        ReportSpecification newSpecification = gson.fromJson(((JSONObject) jsonObject1).toJSONString(), ReportSpecification.class);
                        ReportSpecification specification = specificationService.getByDataEndTimeAndDataStartTimeAndMarketplaceIdsLikeAndReportType(newSpecification.getDataEndTime(), newSpecification.getDataStartTime(), newSpecification.getMarketplaceIds(), newSpecification.getReportType());
                        if (specification == null) {
                            mongo.insert(jsonObject1, collectionName.toString());
                        }
                        break;
                    case SALES_AND_TRAFFIC_BY_ASIN_COLLECTION_NAME:
                        ((JSONArray) jsonObject1).forEach(jsonObject -> {
                            SalesAndTrafficByAsin newSalesAndTrafficByAsin = gson.fromJson(((JSONObject) jsonObject).toJSONString(), SalesAndTrafficByAsin.class);
                            SalesAndTrafficByAsin salesAndTrafficByAsin = salesAndTrafficByAsinService.getByParentAsinAndSalesByAsinAndTrafficByAsin(newSalesAndTrafficByAsin.getParentAsin(), newSalesAndTrafficByAsin.getSalesByAsin(), newSalesAndTrafficByAsin.getTrafficByAsin());
                            if (salesAndTrafficByAsin == null) {
                                mongo.insert(jsonObject, collectionName.toString());
                            }
                        });
                        break;
                    case SALES_AND_TRAFFIC_BY_DATE_COLLECTION_NAME:
                        ((JSONArray) jsonObject1).forEach(jsonObject -> {
                            SalesAndTrafficByDate newSalesAndTrafficByDate = gson.fromJson(((JSONObject) jsonObject).toJSONString(), SalesAndTrafficByDate.class);
                            SalesAndTrafficByDate salesAndTrafficByDate = salesAndTrafficByDateService.getByDateAndSalesByDateAndTrafficByDate(newSalesAndTrafficByDate.getDate(), newSalesAndTrafficByDate.getSalesByDate(), newSalesAndTrafficByDate.getTrafficByDate());
                            if (salesAndTrafficByDate == null) {
                                mongo.insert(jsonObject, collectionName.toString());
                            }
                        });
                        break;
                    default:
                        throw new RuntimeException("Not supported collection name");
                }
            });
        }
    }

    private Object parseFromJson() {
        JSONParser jsonParser = new JSONParser();
        Object jsonData = null;
        try {
            FileReader reader = new FileReader(filePath);
            jsonData = jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return jsonData;
    }

    private void saveDataToDB(JSONObject jsonData) {
        jsonData.forEach((collectionName, jsonObject1) -> {
            if (jsonObject1 instanceof JSONObject) {
                mongo.insert(jsonObject1, collectionName.toString());
            } else if (jsonObject1 instanceof JSONArray) {
                ((JSONArray) jsonObject1).forEach(jsonObject -> {
                    mongo.insert(jsonObject, collectionName.toString());
                });
            }
        });
    }

    @PostConstruct
    public void init() {
        initDBFromFile();
    }
}
