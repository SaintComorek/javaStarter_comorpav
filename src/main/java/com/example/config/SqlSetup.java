package com.example.config;

import org.springframework.lang.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlSetup {
        //jdbc:postgresql://localhost:5432/postgres
        //static final private String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        static final private String DB_URL_USERS = "jdbc:postgresql://localhost:5432/Users";
        static final private String USER = "postgres";
        static final private String PASS = "root";

        public static void createDatabase(String databaseName) throws SQLException {
            // Open a connection
            try (Connection conn = DriverManager.getConnection(DB_URL_USERS, USER, PASS);
                 Statement stmt = conn.createStatement();
            ) {
                String sql = "CREATE DATABASE " + databaseName.toUpperCase();
                stmt.executeUpdate(sql);
                System.out.println("Database created successfully...");
            } catch (SQLException e) {
                System.out.println("Exception catch " + e.getMessage());
            }
        }

        public void connect() {
            try (Connection conn = DriverManager.getConnection(DB_URL_USERS, USER, PASS);
                 Statement stmt = conn.createStatement();
            ) {
                System.out.println("Connected ");
            } catch (SQLException e) {
                System.out.println("Exception catch " + e.getMessage());
            }
        }


        public void addToDatabase() throws SQLException {
            Connection connection = DriverManager.getConnection(DB_URL_USERS, USER, PASS);
            Statement st = connection.createStatement();
            st.execute("VALUES ('Simpson', 'Mr.', 2001)");
        }

        public void createTable(String databaseName,String tableName) {
            // PreparedStatement create = Connection.prepareStatement("CREATE TABLE IF NOT EXIST Users(id int NOT NULL AUTO_INCREMENT, first varchar(255) , last varchar(255), PRIMARY KEY(id))");
            try (Connection conn = DriverManager.getConnection(DB_URL_USERS, USER, PASS);
                 Statement statement = conn.createStatement();
            ) {
                String query = "Create table " + tableName + "(userid SERIAL, name varchar(200),lastname varchar(200), age int , status varchar(200), primary key(userid));";
                // String query2 = "CREATE TABLE IF NOT EXIST Users(id int NOT NULL AUTO_INCREMENT, first varchar(255) , last varchar(255), PRIMARY KEY(id)";
                statement.executeUpdate(query);
                System.out.println("New table " + tableName + " created ");
            } catch (Exception e) {
                System.out.println("Exception catch " + e.getMessage());
            }
        }
        @NonNull
        public void insert_row(String tableName, String name, String lastName, int age, String status) {
            Statement statement;
            try (Connection conn = DriverManager.getConnection(DB_URL_USERS, USER, PASS);
            ) {
                String query = String.format("insert into %s(name,lastname,age,status) values('%s' , '%s' , '%s' , '%s');", tableName, name, lastName, Integer.toString(age), status);
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println("Rows are inserted ");
            } catch (SQLException e) {
                System.out.println("Exception catch " + e.getMessage());
            }
        }

        @NonNull
        public void insert_row(String tableName, String name, String lastName, int age) {
            Statement statement;
            try (Connection conn = DriverManager.getConnection(DB_URL_USERS, USER, PASS);
            ) {
                String query = String.format("insert into %s(name,lastname,age,status) values('%s' , '%s' , '%s' , '%s');", tableName, name, lastName, Integer.toString(age), "basic");
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println("Rows are inserted ");
            } catch (SQLException e) {
                System.out.println("Exception catch " + e.getMessage());
            }
        }
    }


