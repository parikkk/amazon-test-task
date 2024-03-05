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
@Document(collection = "salesAndTrafficByAsin")
public class SalesAndTrafficByAsin {

    @Id
    private Long id;

    private String parentAsin;

    private Object salesByAsin;

    private Object trafficByAsin;
}
