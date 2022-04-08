package com.incs.spendtracking.repository;

import com.incs.spendtracking.common.UserHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserHistoryRepository extends MongoRepository<UserHistory, String> {
}
