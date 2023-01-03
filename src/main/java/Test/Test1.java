package Test;

import DAO.ImplRankLevelsDao;
import Entities.Student;

import java.sql.SQLException;
import java.text.ParseException;
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
            System.out.println("12. Xem các cơ sở dữ liệu đã xóa");
            System.out.println("13. Thoát khỏi chương trình");
            List<Student> list = implRankLevelsDao.selectAll();
            Scanner sc = new Scanner(System.in);
            int chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    Student students = Student.input();
                    implRankLevelsDao.insert(students);
                    break;
                case 2:
                    System.out.println("Nhập id bạn muốn sửa");
                    int id = Integer.parseInt(sc.nextLine());
                    Student studentuk = implRankLevelsDao.selectByID(id);
                    if (studentuk != null) {
                        Student studentsua = Student.input();
                        studentuk.setName(studentsua.getName());
                        studentuk.setCode(studentsua.getCode());
                        studentuk.setPhone(studentsua.getPhone());
                        studentuk.setAddress(studentsua.getAddress());
                        studentuk.setCreate_at(studentsua.getCreate_at());
                        studentuk.setUpdate_at(studentsua.getUpdate_at());
                        studentuk.setAge(studentsua.getAge());
                        implRankLevelsDao.update(studentuk);
                        break;
                    }
                    System.out.println("Không có id trong cơ sở dữ liệu ");
                    break;
                case 3:
                    System.out.println("Nhập id bạn muốn xóa");
                    int idxoa = Integer.parseInt(sc.nextLine());
                    // find sql heo id
                    try {
                        Student studentla = implRankLevelsDao.selectByID(idxoa);
                        if (studentla.getName() != null) {
                            //Nếu mà có thì cập nhâp lại ngày xóa
                            implRankLevelsDao.delete(idxoa);
                            break;
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
                    Student studentla = implRankLevelsDao.selectByID(id_tim);
                    if (studentla != null) {
                        System.out.println(studentla.toString());
                        break;
                    } else {
                        System.out.println("Không có id trong cơ sở dỡ liệu");
                    }
                    break;
                case 5:
                    for (Student std : list) {
                        System.out.println(std.toString());
                    }
                    break;
                case 6:
                    System.out.println("Nhập tên bạn muốn tìm");
                    String name_search = sc.nextLine();
                    List<Student> listname = implRankLevelsDao.selectByName(name_search);
                    for (Student std : listname) {
                        System.out.println(std.toString());
                        break;
                    }
                    if (listname.isEmpty()) {
                        System.out.println("Không tìm thấy tên bạn muốn tìm");
                    }
                    break;
                case 7:
                    System.out.println("Nhập địa chỉ bạn muốn tìm");
                    String address_search = sc.nextLine();
                    List<Student> listaddress = implRankLevelsDao.selectByAddress(address_search);
                    for (Student std : listaddress) {
                        System.out.println(std.toString());
                        break;
                    }
                    if (listaddress.isEmpty()) {
                        System.out.println("Không tìm thấy địa chỉ bạn muốn tìm");
                    }
                    break;
                case 8:
                    System.out.println("Nhập mã code muốn tìm");
                    String macode = sc.nextLine();
                    Student student = implRankLevelsDao.selectByCode(macode);
                    if (student != null) {
                        System.out.println(student.toString());
                    } else {
                        System.out.println("Không có mã code bạn tìm");
                    }
                    break;
                case 9:
                    List<Student> listcreaser = implRankLevelsDao.increaserAge();
                    for (Student stdtang : listcreaser) {
                        System.out.println(stdtang.toString());
                    }
                    break;
                case 10:
                    List<Student> studentList = implRankLevelsDao.decreaserAge();
                    for (Student student1 : studentList) {
                        System.out.println(student1.toString());
                    }
                    break;
                case 11:
                    System.out.println("Nhập name or address:");
                    String nametim = sc.nextLine();
                    List<Student> list1 = implRankLevelsDao.selectByNameOrByAddress(nametim);
                    for (Student timstd : list1) {
                        System.out.println(timstd.toString());
                        break;
                    }
                    if (list1.isEmpty()) {
                        System.out.println("Không tìm thấy kết quả ?");
                    }
                    break;
                case 12:
                    List<Student> list2 = implRankLevelsDao.selectDeletedAll();
                    for (Student student1 : list2) {
                        System.out.println(student1.toString());
                    }
                    break;
                case 13:
                    thoat = 12;
            }
        } while (thoat < 11);
        System.out.println("Xin chào tạm biệt !");
    }
}
