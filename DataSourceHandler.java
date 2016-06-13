
package vicody.pool.db;


import java.util.concurrent.*;

/**
 * Created by wzm on 2016/6/6.
 */
public class DataSourceHandler implements Runnable {
    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DataSource dataSource = PoolsManager.getDataSources();
            if (dataSource != null) {
                System.out.println(String.format("host: [%s] port: [%s]",dataSource.getHost(),dataSource.getPort()));
            } else {
                System.out.println("ds is null");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);
        DBPoolsTask task = new DBPoolsTask();
        service.scheduleWithFixedDelay(task,0,1000, TimeUnit.MILLISECONDS);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i=0; i<100; i++) {
            executorService.execute(new DataSourceHandler());
        }
    }
}
