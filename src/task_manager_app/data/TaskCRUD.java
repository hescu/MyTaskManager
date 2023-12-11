package task_manager_app.data;

import task_manager_app.model.Task;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskCRUD {

    public static List<Task> queryAllTasks() {
        DataSource dataSource = DataSourceServer.getDataSource();
        List<Task> listOfTasks = new ArrayList<Task>();

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM task";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Task taskObj = new Task();
                    taskObj.setTaskId(resultSet.getInt("task_id"));
                    taskObj.setTitle(resultSet.getString("title"));
                    taskObj.setDescription(resultSet.getString("description"));
                    taskObj.setCreationDate(resultSet.getTimestamp("creation_date").toString());
                    taskObj.setCreationDate(resultSet.getTimestamp("due_date").toString());
                    taskObj.setPriority(resultSet.getInt("priority"));
                    taskObj.setStatus(resultSet.getString("status"));
                    listOfTasks.add(taskObj);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return listOfTasks;
    }

    public static int createTask(Task newTask) {
        DataSource dataSource = DataSourceServer.getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO task " +
                    "(title, description, due_date, priority, status)" +
                    "VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newTask.getTitle());
                preparedStatement.setString(2, newTask.getDescription());
                preparedStatement.setDate(3, Date.valueOf(newTask.getDueDate()));
                preparedStatement.setInt(4, newTask.getPriority());
                preparedStatement.setString(5, newTask.getStatusAsString());

                return preparedStatement.executeUpdate();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            return 0;
        }
    }

    public static int deleteTaskFromDB(int IdToDelete) {
        DataSource dataSource = DataSourceServer.getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM task WHERE task_id = ?;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, IdToDelete);

                return preparedStatement.executeUpdate();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            return 0;
        }
    }
}
