package com.incs.spendtracking.common;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "purchase_history")
public class UserHistory {

    @Id
    private String userHistoryId;
    private String userName;


}
