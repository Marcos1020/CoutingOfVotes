package com.sanches.coutingOfVotes.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Slf4j
public class ConverterUtil {
  public static final String FORMATO_DATA = "yyyy-MM-dd HH:mm:ss";
  public static Timestamp nowTime() {
    return new Timestamp( System.currentTimeMillis() );
  }

}