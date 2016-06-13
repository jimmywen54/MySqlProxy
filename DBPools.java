/*
 * Copyright (c) 2015 yy.com. 
 *
 * All Rights Reserved.
 *
 * This program is the confidential and proprietary information of 
 * YY.INC. ("Confidential Information").  You shall not disclose such
 * Confidential Information and shall use it only in accordance with
 * the terms of the license agreement you entered into with yy.com.
 */
package vicody.pool.db;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2016/4/18.
 */
public class DBPools {

    private static List<DataSource> dataSources = new ArrayList<DataSource>();

    public static void add(DataSource dataSource) {
        dataSources.add(dataSource);
    }

    public static void romove(DataSource dataSource) {
        dataSources.remove(dataSource);
    }

    public static List<DataSource> getDataSources() {
        return dataSources;
    }

}
