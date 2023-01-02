package Entities;

import DAO.Validate;

import java.util.Date;
import java.util.Scanner;

public class student {
    private int id;
    private String name;
    private String code;
    private String phone;
    private String address;
    private Date create_at;
    private Date update_at;

    private int age;

    public student(int id, String name, String code, String phone, String address, Date create_at, Date update_at) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
    }

    public student(int id, String name, String code, String phone, String address, Date create_at, Date update_at, int age) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
        this.age = age;
    }

    public student(String name, String code, String phone, String address, Date create_at, Date update_at, int age) {
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public student(String name, String code, String phone, String address, Date create_at, Date update_at) {
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
    }

    public student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }


    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "student{" + "id=" + id + ", name='" + name + '\'' + ", code='" + code + '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\'' + ", create_at=" + create_at + ", update_at=" + update_at + ", age=" + age + '}';
    }

    public static student input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập name :");
        String name = sc.nextLine();
        System.out.println("Nhập code : ");
        String code = sc.nextLine();
        //Check mã code
        Validate.checkMaCode(code);
        String codecheck = Validate.checkKyTuDacBiet(code);
        //////
        System.out.println("Nhập phone : ");
        String phone = sc.nextLine();
        String phonela = Validate.checkPhone(phone);
        System.out.println("Nhập địa chỉ : ");
        String address = sc.nextLine();
        Date create_at = new Date();
        Date update_at = new Date();
        System.out.println("Nhập tuổi :");
        int age = 0;
        try {
            age = Integer.parseInt(sc.nextLine());
            Validate.checkAge(age);
        } catch (Exception exception) {
            System.out.println("Tuổi không được phép là chuỗi");
            System.out.println("Nhập tuổi");
            age = Integer.parseInt(sc.nextLine());
            Validate.checkAge(age);
        }
        student students = new student(name, codecheck, phonela, address, create_at, update_at, age);
        return students;
    }
}
