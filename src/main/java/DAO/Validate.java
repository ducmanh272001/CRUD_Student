package DAO;

import Connection_DB.ConnectionPostgres;
import Entities.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validate {

    //Check nhập code


    public static String checkMaCode(String code) {
        ImplRankLevelsDao implRankLevelsDao = new ImplRankLevelsDao();
        Scanner sc = new Scanner(System.in);
        while (true) {
            student student = implRankLevelsDao.selectByCode(code);
            if (student.getName() == null) {
                break;
            }
            System.out.println("Mã code đã có trong DB : ");
            System.out.println("Nhập code : ");
            code = sc.nextLine();
        }
        return code;
    }

    //Check tuổi
    public static void checkAge(int age) {
        try {
            Scanner sc = new Scanner(System.in);
            while (age < 0) {
                System.out.println("Tuổi không được âm :D");
                System.out.println("Nhập tuổi :");
                age = Integer.parseInt(sc.nextLine());
            }
        } catch (Exception exception) {
            System.out.println("Lỗi Sai kiểu dữ liệu của tuổi");
        }
    }


    //Check mã code kí tự đặc biệt
    public static String checkKyTuDacBiet(String macode) {
        Pattern pattern = Pattern.compile("^[a-zA-z0-9]+$");
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (pattern.matcher(macode).find()) {
                return macode;
            } else {
                System.out.println("Mã code không được chứa kí tự đặc biệt");
                System.out.println("Nhập code : ");
                macode = sc.nextLine();
            }
        }
    }


    //Check số điện thoại

    public static String checkPhone(String phone) {
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        Pattern pattern = Pattern.compile(reg);
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (pattern.matcher(phone).find()) {
                return phone;
            } else {
                System.out.println("Loi: Khong dung dinh dang!");
                System.out.println("Nhập phone : ");
                phone = sc.nextLine();
            }
        }
    }

}
