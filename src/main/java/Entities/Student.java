package Entities;

import DAO.Validate;

import java.util.Date;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private String code;
    private String phone;
    private String address;
    private Date create_at;
    private Date update_at;
    private Date deleted_at;

    private int age;

    public Student(int id, String name, String code, String phone, String address, Date create_at, Date update_at, Date deleted_at) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
        this.deleted_at = deleted_at;
    }

    public Student(int id, String name, String code, String phone, String address, Date create_at, Date update_at, int age) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
        this.age = age;
    }

    public Student(int id, String name, String code, String phone, String address, Date create_at, Date update_at, int age, Date deleted_at) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
        this.age = age;
        this.deleted_at = deleted_at;
    }


    public Student(String name, String code, String phone, String address, Date create_at, Date update_at, int age) {
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
        this.age = age;
    }

    public Student(String name, String code, String phone, String address, Date create_at, Date update_at, int age, Date deleted_at) {
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
        this.age = age;
        this.deleted_at = deleted_at;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, String code, String phone, String address, Date create_at, Date update_at, Date deleted_at) {
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.create_at = create_at;
        this.update_at = update_at;
        this.deleted_at = deleted_at;
    }

    public Student() {
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

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "student{" + "id=" + id + ", name='" + name + '\'' + ", code='" + code + '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\'' + ", create_at=" + create_at + ", update_at=" + update_at + ", deleted_at=" + deleted_at + ", age=" + age + '}';
    }

    public static Student input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nh???p name :");
        String name = sc.nextLine();
        System.out.println("Nh???p code : ");
        String code = sc.nextLine();
        //Check m?? code
        code = Validate.checkCode(code);
        String codecheck = Validate.checkCharacters(code);
        //////
        System.out.println("Nh???p phone : ");
        String phone = sc.nextLine();
        String phonela = Validate.checkPhone(phone);
        System.out.println("Nh???p ?????a ch??? : ");
        String address = sc.nextLine();
        Date create_at = new Date();
        Date update_at = new Date();
        System.out.println("Nh???p tu???i :");
        int age = 0;
        try {
            age = Integer.parseInt(sc.nextLine());
            Validate.checkAge(age);
        } catch (Exception exception) {
            System.out.println("Tu???i kh??ng ???????c ph??p l?? chu???i");
            System.out.println("Nh???p tu???i");
            age = Integer.parseInt(sc.nextLine());
            Validate.checkAge(age);
        }
        Student students = new Student(name, codecheck, phonela, address, create_at, update_at, age);
        return students;
    }
}
