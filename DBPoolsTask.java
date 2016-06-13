
package vicody.pool.db;

import com.google.common.collect.Lists;
import org.apache.commons.net.telnet.TelnetClient;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wzm on 2016/6/7.
 */
public class DBPoolsTask implements Runnable {
    @Override
    public void run() {
        System.out.println("定时任务执行");
        try {
            List<DataSource> dataSources = Lists.newArrayList();
            DataSource dataSource1 = new DataSource("127.0.0.1","3306","test","root","root");
            DataSource dataSource2 = new DataSource("192.168.83.128","3306","vicody","root","root");
            dataSources.add(dataSource1);
            dataSources.add(dataSource2);
            for (DataSource dataSource : dataSources) {
                int result = DataSourceClient.getTestResult(dataSource);
                if (result == 1) {
                    PoolsManager.addDataSource(dataSource);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);
        DBPoolsTask task = new DBPoolsTask();
        service.scheduleWithFixedDelay(task,0,3000, TimeUnit.MILLISECONDS);
    }
}
