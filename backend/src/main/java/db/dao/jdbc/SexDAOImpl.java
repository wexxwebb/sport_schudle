package db.dao.jdbc;

import common.InsertType;
import common.Log;
import common.Logged;
import common.Result;
import db.connectionManager.ConnectionManager;
import db.dao.SexDAO;
import db.entities.Sex;
import db.entities.Impl.SexImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

@Component
public class SexDAOImpl implements SexDAO {

    @Logged
    private Logger logger;

    private ConnectionManager connectionManager;

    public SexDAOImpl() {

    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    @Autowired
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Result<List<Sex>> getAll() {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery(
                        "SELECT " +
                                "id, " +
                                "sex " +
                                "FROM sex"
                );

                List<Sex> sexList = new ArrayList<>();
                while (result.next()) {
                    Sex sex = new SexImpl(
                            result.getInt("id"),
                            result.getString("sex")
                    );
                    sexList.add(sex);
                }
                logger.debug("Sex list reading from database");
                return new Result<>(sexList, true, "Success");
            } catch (ClassNotFoundException e) {
                logger.error(new Log(e, "Database driver lost"));
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, "Database query executing error"));
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<Sex> insert(Sex sex, InsertType insertType) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO sex (sex) VALUES (?) RETURNING id"
                    );

                    preparedStatement.setString(1, sex.getSex());
                } else if (insertType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO sex (id, sex) VALUES (?, ?) RETURNING id"
                    );
                    preparedStatement.setLong(1, sex.getId());
                    preparedStatement.setString(2, sex.getSex());
                }

                preparedStatement.addBatch();
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    sex.setId(resultSet.getLong("id"));
                    return new Result<>(sex, true, "Success");
                }
                return new Result<>(null, false, "Success");

            } catch (ClassNotFoundException e) {
                logger.error(new Log(e, sex));
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, sex, "retry = " + retry));
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }
}