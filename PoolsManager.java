package vicody.pool.db;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wzm on 2016/6/6.
 */
public class PoolsManager {
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    public static void addDataSource(DataSource dataSource) {
        lock.writeLock().lock();
        DBPools.add(dataSource);
        System.out.println(String.format("add ds %s",dataSource.getHost()));
        lock.writeLock().unlock();
    }

    public static DataSource getDataSources() {
        try {
            lock.readLock().lock();
            List<DataSource> dataSources = DBPools.getDataSources();
            if (dataSources.size() > 0) {
                return dataSources.get(dataSources.size() - 1);
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }
}
