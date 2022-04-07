package com.incs.spendtracking.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public final class CommonUtils {

    private static final Logger LOG = LogManager.getLogger();

    public static String generateUUID(){
        // dash less UUID
        return UUID.randomUUID().toString().replaceAll("-" , "");
    }

}
