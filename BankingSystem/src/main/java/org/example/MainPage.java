package org.example;

import java.sql.SQLException;
import java.util.Scanner;

import static org.example.CurrentAccount.current;
import static org.example.CurrentAccount.currentRegister;
import static org.example.SavingAccount.saving;
import static org.example.SavingAccount.savingRegister;

public class MainPage {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        System.out.println("Enter 1 for Login");
        System.out.println("Enter 2 for Open Account");
        int num=sc.nextInt();
        switch (num){
            case 1:
                System.out.print("Enter the customer id: ");
                int id=sc.nextInt();
                System.out.println("Enter the type of account");
                System.out.println("1: Saving");
                System.out.println("2: Current");
                int num1=sc.nextInt();
                switch (num1){
                    case 1:
                        saving(id);
                        break;
                    case 2:
                        current(id);
                        break;
                    default:
                        System.out.println("Invalid option...");
                }
                break;
            case 2:
                register();
                break;
            default:
                System.out.println("Enter a valid option...");
        }
    }
    private static void login(int id,String type){
        if(type=="saving"){
            saving(id);
        }else if(type=="current"){
            current(id);
        }else{
            System.out.println("Invalid account type");
        }
    }
    private static void register() throws SQLException {
        System.out.println("Enter the name: ");
        String name= sc.next();
        System.out.println("Enter the account opening amount: ");
        int amt=sc.nextInt();
        System.out.println("Enter the type of account");
        System.out.println("1: Saving");
        System.out.println("2: Current");
        int num=sc.nextInt();
        switch (num){
            case 1:
                savingRegister(name,amt);
                break;
            case 2:
                currentRegister(name,amt);
                break;
            default:
                System.out.println("Invalid option...");
        }
    }
}
