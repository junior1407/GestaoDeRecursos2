package Utilities;

import jdk.internal.util.xml.impl.Input;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputProcessor {
    public Scanner scannerInt, scannerString;

    public InputProcessor() {
        scannerString=new Scanner(System.in);
        scannerInt = new Scanner(System.in);
    }

    public LocalDateTime getDate()
    {
        int arr[] = new int[5];
        LocalDateTime l;
        System.out.println("Type a day month year hour min");
        for (int i=0; i<5;i++)
        {
            arr[i]=getInteger("Type a valid intenger!");
        }
        try {
           l = LocalDateTime.of(arr[2], arr[1], arr[0], arr[3], arr[4]);
        }
        catch (DateTimeException e)
        {
            System.out.println("Type a valid date!");
            return getDate();
        }

        return l;
    }

    public int getInteger(String errMessage)
    {
        try {
            return scannerInt.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.println(errMessage);
            scannerInt.nextLine();
            return getInteger(errMessage);
        }
    }

    public String getString( Boolean necessary)
    {
        try {
            String temp = scannerString.nextLine();
            if ((temp.isEmpty()) && (necessary))
            {
                throw new IllegalArgumentException("String is empty");
            }
            return temp;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("The string can't be empty!");
            return getString(necessary);
        }
    }


}
