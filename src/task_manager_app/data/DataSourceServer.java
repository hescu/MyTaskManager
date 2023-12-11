package task_manager_app.data;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DataSourceServer {
    public static MysqlDataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("taskmanager");
        dataSource.setUser("");
        dataSource.setPassword("");

        return dataSource;
    }
}
