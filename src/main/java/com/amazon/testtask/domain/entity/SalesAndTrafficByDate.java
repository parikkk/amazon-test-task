package com.amazon.testtask.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "salesAndTrafficByDate")
public class SalesAndTrafficByDate {

    @Id
    private Long id;

    private String date;

    private Object salesByDate;

    private Object trafficByDate;
}
