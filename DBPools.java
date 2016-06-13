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
