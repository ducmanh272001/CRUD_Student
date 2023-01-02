package DAO;

import Connection_DB.ConnectionPostgres;
import Entities.student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LuuTru {
    private Connection con;

    public LuuTru() {
        con = ConnectionPostgres.getConnection();
    }


    public List<student> listStudent(String SQL) {
        List<student> list = new ArrayList<student>();
        try {
            PreparedStatement statement = con.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Date create_at = resultSet.getDate("created_at");
                Date update_at = resultSet.getDate("update_at");
                int age = resultSet.getInt("age");
                student students = new student(id, name, code, phone, address, create_at, update_at, age);
                list.add(students);
            }
        } catch (Exception ex) {
            System.out.println("Lỗi không kết nối db");
        }
        return list;
    }


    public List<student> listThu(ResultSet resultSet) {
        List<student> list = new ArrayList<student>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Date create_at = resultSet.getDate("created_at");
                Date update_at = resultSet.getDate("update_at");
                int age = resultSet.getInt("age");
                student students = new student(id, name, code, phone, address, create_at, update_at, age);
                list.add(students);
            }
        } catch (Exception ex) {
            System.out.println("Lỗi không kết nối db");
        }
        return list;
    }


    public int save(String SQL, student std) {
        int check = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1, std.getName());
            preparedStatement.setString(2, std.getCode());
            preparedStatement.setString(3, std.getPhone());
            preparedStatement.setString(4, std.getAddress());
            preparedStatement.setDate(5, new Date(std.getCreate_at().getTime()));
            preparedStatement.setDate(6, new Date(std.getUpdate_at().getTime()));
            preparedStatement.setInt(7, std.getAge());
            check = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            System.out.println("Lỗi không thêm đc");
        }
        return check;
    }

    public int update(String SQL, student std) {
        int check = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1, std.getName());
            preparedStatement.setString(2, std.getCode());
            preparedStatement.setString(3, std.getPhone());
            preparedStatement.setString(4, std.getAddress());
            preparedStatement.setDate(5, new Date(std.getCreate_at().getTime()));
            preparedStatement.setDate(6, new Date(std.getUpdate_at().getTime()));
            preparedStatement.setInt(7, std.getAge());
            preparedStatement.setInt(8, std.getId());
            check = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            System.out.println("Lỗi không thêm đc");
        }
        return check;
    }


    //
    public student getStudent(ResultSet resultSet) {
        student studentget = new student();
        try {
            while (resultSet.next()) {
                studentget.setId(resultSet.getInt("id"));
                studentget.setName(resultSet.getString("name"));
                studentget.setCode(resultSet.getString("code"));
                studentget.setPhone(resultSet.getString("phone"));
                studentget.setAddress(resultSet.getString("address"));
                studentget.setCreate_at(resultSet.getDate("created_at"));
                studentget.setUpdate_at(resultSet.getDate("update_at"));
                studentget.setAge(resultSet.getInt("age"));
            }
        } catch (Exception exception) {
            System.out.println("Lỗi xem lại");
        }
        return studentget;
    }
}
