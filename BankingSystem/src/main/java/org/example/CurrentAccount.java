package org.example;

import java.sql.*;
import java.util.Scanner;

public class CurrentAccount {
    public static final String url = "jdbc:mysql://localhost:3306/bank_db";
    public static final String username = "root";
    public static final String password = "vansh2001";
    public static Connection connection;
    public static PreparedStatement statement;
    public static ResultSet resultSet;
    static Scanner sc = new Scanner(System.in);
    public static void current(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            String str;
            do {
                System.out.println("Enter 1 for Deposit Amount");
                System.out.println("Enter 2 for Withdrawal Amount");
                System.out.println("Enter 3 to view Account Detail");
                int num = sc.nextInt();
                switch (num) {
                    case 1:
                        System.out.println("Enter the amount you want to deposit: ");
                        int depositAmount = sc.nextInt();
                        deposit(connection, id, depositAmount);
                        break;
                    case 2:
                        System.out.println("Enter the amount you want to withdraw: ");
                        int withdrawalAmount = sc.nextInt();
                        withdraw(connection, id, withdrawalAmount);
                        break;
                    case 3:
                        viewDetails(connection, id);
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
                System.out.println("Do you want to exit: YES or NO: ");
                str = sc.next().toUpperCase();
            } while (!str.equals("YES"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                sc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void viewDetails(Connection connection, int id) throws SQLException {
        try {
            statement = connection.prepareStatement("SELECT * FROM current WHERE id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("***************Account Detail*******************");
                System.out.println("Customer id is: " + resultSet.getInt(1));
                System.out.println("Customer name is: " + resultSet.getString(2));
                System.out.println("Account Balance is: " + resultSet.getInt(3));
                System.out.println("************************************************");
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        }
    }

    private static void withdraw(Connection connection, int id, int amt) throws SQLException {
        try {
            statement = connection.prepareStatement("SELECT AMT FROM current WHERE id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            int currentAmount = 0;
            if (resultSet.next()) {
                currentAmount = resultSet.getInt("AMT");
            }

            if (currentAmount >= amt) {
                int newAmount = currentAmount - amt;

                statement = connection.prepareStatement("UPDATE current SET AMT=? WHERE id=?");
                statement.setInt(1, newAmount);
                statement.setInt(2, id);

                int result = statement.executeUpdate();

                if (result > 0) {
                    System.out.println("Withdrawal Successful.");
                } else {
                    System.out.println("Something went wrong.");
                }
            } else {
                System.out.println("Insufficient funds.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (statement != null) statement.close();
        }
    }

    private static void deposit(Connection connection, int id, int amt) throws SQLException {
        try {

            statement = connection.prepareStatement("SELECT AMT FROM current WHERE id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            int currentAmount = 0;
            if (resultSet.next()) {
                currentAmount = resultSet.getInt("AMT");
            }

            int newAmount = currentAmount + amt;

            statement = connection.prepareStatement("UPDATE current SET AMT=? WHERE id=?");
            statement.setInt(1, newAmount);
            statement.setInt(2, id);

            int result = statement.executeUpdate();

            if (result > 0) {
                System.out.println("Deposit Successful.");
            } else {
                System.out.println("Something went wrong.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (statement != null) statement.close();
        }
    }
    public static void currentRegister(String name,int amount) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement=connection.prepareStatement("Insert into current (name,AMT)values(?,?)");
            statement.setString(1,name);
            statement.setInt(2,amount);
            int result=statement.executeUpdate();
            if(result>0){
                System.out.println("Account open successfully");
            }else{
                System.out.println("Something went wrong");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}