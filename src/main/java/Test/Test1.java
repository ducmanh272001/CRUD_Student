package Test;

import DAO.ImplRankLevelsDao;
import DAO.Validate;
import Entities.student;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) throws ParseException, SQLException {
        int thoat = 5;
        ImplRankLevelsDao implRankLevelsDao = new ImplRankLevelsDao();
        do {
            System.out.println("======HELLO=====");
            System.out.println("1. Thêm dữ liệu");
            System.out.println("2. Sửa dữ liệu");
            System.out.println("3. Xóa dữ liệu");
            System.out.println("4. Tìm kiếm theo id");
            System.out.println("5. Hiển thị danh sách");
            System.out.println("6. Tìm kiếm theo name");
            System.out.println("7. Tìm kiếm theo address");
            System.out.println("8. Tìm kiếm theo mã code");
            System.out.println("9. Tăng dần theo tuổi");
            System.out.println("10. Giảm dần theo tuổi");
            System.out.println("11. Tìm kiếm theo name và address");
            System.out.println("12. Thoát khỏi chương trình");
            List<student> list = implRankLevelsDao.selectAll();
            Scanner sc = new Scanner(System.in);
            int chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
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
                    implRankLevelsDao.insert(students);
                    break;
                case 2:
                    System.out.println("Nhập id bạn muốn sửa");
                    int id = Integer.parseInt(sc.nextLine());
                    student studentuk = implRankLevelsDao.selectByID(id);
                    if (studentuk.getName() != null) {
                        System.out.println("Nhập name :");
                        String namesua = sc.nextLine();
                        System.out.println("Nhập code : ");
                        String codesua = sc.nextLine();
                        //CHECK KO được trùng CODE
                        codesua = Validate.checkMaCode(codesua);
                        codesua = Validate.checkKyTuDacBiet(codesua);
                        System.out.println("Nhập phone : ");
                        String phonesua = sc.nextLine();
                        String phonelas = Validate.checkPhone(phonesua);
                        System.out.println("Nhập địa chỉ : ");
                        String addresssua = sc.nextLine();
                        System.out.println("Nhập tuổi :");
                        int agesua = Integer.parseInt(sc.nextLine());
                        try {
                            Validate.checkAge(agesua);
                        } catch (Exception exception) {
                            System.out.println("Tuổi ko được phép là chuỗi");
                        }
                        Date create_at_sua = new Date();
                        Date update_at_sua = new Date();
                        studentuk.setName(namesua);
                        studentuk.setCode(codesua);
                        studentuk.setPhone(phonelas);
                        studentuk.setAddress(addresssua);
                        studentuk.setCreate_at(create_at_sua);
                        studentuk.setUpdate_at(update_at_sua);
                        studentuk.setAge(agesua);
                        implRankLevelsDao.update(studentuk);
                    }
                    System.out.println("Không có id trong cơ sở dữ liệu ");
                    break;
                case 3:
                    System.out.println("Nhập id bạn muốn xóa");
                    int idxoa = Integer.parseInt(sc.nextLine());
                    // find sql heo id
                    try {
                        student studentla = implRankLevelsDao.selectByID(idxoa);
                        if (studentla.getName() != null) {
                            implRankLevelsDao.delete(idxoa);
                        } else {
                            System.out.println("Không có id trong cơ sở dỡ liệu");
                        }

                    } catch (Exception exception) {
                        System.out.println("Không có id trong cơ sở dữ liệu");
                    }
                    break;
                case 4:
                    System.out.println("Nhập id bạn muốn tìm : ");
                    int id_tim = Integer.parseInt(sc.nextLine());
                    student studentla = implRankLevelsDao.selectByID(id_tim);
                    if (studentla.getName() != null) {
                        System.out.println(studentla.toString());
                    } else {
                        System.out.println("Không có id trong cơ sở dỡ liệu");
                    }
                    break;
                case 5:
                    for (student std : list) {
                        System.out.println(std.toString());
                    }
                    break;
                case 6:
                    System.out.println("Nhập tên bạn muốn tìm");
                    String name_search = sc.nextLine();
                    List<student> listname = implRankLevelsDao.selectByName(name_search);
                    for (student std : listname) {
                        System.out.println(std.toString());
                    }
                    break;
                case 7:
                    System.out.println("Nhập địa chỉ bạn muốn tìm");
                    String address_search = sc.nextLine();
                    List<student> listaddress = implRankLevelsDao.selectByAddress(address_search);
                    for (student std : listaddress) {
                        System.out.println(std.toString());
                    }
                    break;
                case 8:
                    System.out.println("Nhập mã code muốn tìm");
                    String macode = sc.nextLine();
                    student student = implRankLevelsDao.selectByCode(macode);
                    if (student.getName() != null) {
                        System.out.println(student.toString());
                    } else {
                        System.out.println("Không có mã code bạn tìm");
                    }
                    break;
                case 9:
                    List<student> listcreaser = implRankLevelsDao.increaserAge();
                    for (student stdtang : listcreaser) {
                        System.out.println(stdtang.toString());
                    }
                    break;
                case 10:
                    List<student> listgiam = implRankLevelsDao.decreaserAge();
                    for (student stdgiam : listgiam) {
                        System.out.println(stdgiam.toString());
                    }
                    break;
                case 11:
                    System.out.println("Nhập name or address:");
                    String nametim = sc.nextLine();
                    List<student> list1 = implRankLevelsDao.selectByNameOrByAddress(nametim);
                    for (student timstd : list1) {
                        System.out.println(timstd.toString());
                    }
                    break;
                case 12:
                    thoat = 12;
            }
        } while (thoat < 11);
        System.out.println("Xin chào tạm biệt !");
    }
}
