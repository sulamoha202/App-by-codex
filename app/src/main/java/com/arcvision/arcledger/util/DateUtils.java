package com.arcvision.arcledger.util; import java.text.SimpleDateFormat; import java.util.*;
public final class DateUtils {private DateUtils(){} public static String today(){return new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(new Date());} public static String now(){return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault()).format(new Date());}}
