package com.colson.service;


import java.util.List;

public interface ValuableBookService {

    void batchCreateFilePath(Integer knowledgeTreeId, List<String> examSessionLimit);
}
