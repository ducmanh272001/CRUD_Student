package DAO;

import Connection_DB.ConnectionPostgres;
import Entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImplRankLevelsDao implements IRankLevelsDao {

    private Connection con;

    public ImplRankLevelsDao() {
        con = ConnectionPostgres.getConnection();
    }

    @Override
    public List<Student> selectAll() {
        if (con != null) {
            String SQL = "SELECT * FROM student";
            LuuTru luuTru = new LuuTru();
            List<Student> studentList = new ArrayList<Student>();
            List<Student> list = luuTru.listStudent(SQL);
            for (Student students : list) {
                if (students.getDeleted_at() == null) {
                    studentList.add(students);
                }
            }
            return studentList;
        } else {
            System.out.println("Không có kết nối với ConnectionPostgres");
        }
        return null;
    }


    @Override
    public Boolean insert(Student std) {
        if (con != null) {
            String SQL = "INSERT INTO student(name, code, phone, address, created_at, update_at, age) VALUES(?,?,?,?,?,?,?)";
            LuuTru luuTru = new LuuTru();
            int check = luuTru.save(SQL, std);
            if (check > 0) {
                System.out.println("Thêm thành công");
                return true;
            } else {
                System.out.println("Thêm thất bại");
                return false;
            }
        } else {
            System.out.println("Không có kết nối với ConnectionPostgres");
        }
        return false;
    }

    @Override
    public Boolean update(Student std) {
        if (con != null) {
            String SQL = "UPDATE student SET name = ?, code = ?, phone = ?, address = ?, created_at = ?, update_at = ?, age = ? WHERE id = ?";
            LuuTru luuTru = new LuuTru();
            int check = luuTru.update(SQL, std);
            if (check > 0) {
                System.out.println("Sửa thành công !");
                return true;
            } else {
                System.out.println("Sửa thất bại :> ");
                return false;
            }

        } else {
            System.out.println("Không có kết nối với ConnectionPostgres");
        }
        return false;
    }

    @Override
    public Boolean delete(int idxoa) {
        if (con != null) {
            try {
                String SQL = "UPDATE student SET deleted_at = ? WHERE id = ?";
                PreparedStatement preparedStatement = con.prepareStatement(SQL);
                java.util.Date ngayxoa = new Date();
                preparedStatement.setDate(1, new java.sql.Date(ngayxoa.getTime()));
                preparedStatement.setInt(2, idxoa);
                int xoatc = preparedStatement.executeUpdate();
                if (xoatc > 0) {
                    System.out.println("Xóa thành công");
                    return true;
                } else {
                    System.out.println("Xóa không thành công");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Không có kết nối với ConnectionPostgres");
            }
        }
        return null;
    }

    @Override
    public Student selectByID(int idtim) {
        try {
            String SQL = "SELECT * FROM student WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setInt(1, idtim);
            ResultSet resultSet = statement.executeQuery();
//            rank_levels rankLevels = new rank_levels();
            LuuTru luuTru = new LuuTru();
            Student studentok = luuTru.getStudent(resultSet);
            return studentok;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Student selectByCode(String macode) {
        try {
            String SQL = "SELECT * FROM student WHERE code = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, macode);
            ResultSet resultSet = statement.executeQuery();
            LuuTru luuTru = new LuuTru();
            Student studentpl = luuTru.getStudent(resultSet);
            return studentpl;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> selectByName(String name) {
        try {
            String SQL = "SELECT * FROM student WHERE name like ? ";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
//            rank_levels rankLevels = new rank_levels();
            LuuTru luuTru = new LuuTru();
            List<Student> list = luuTru.listThu(resultSet);
            return list;


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> selectByAddress(String address) {
        List<Student> list = new ArrayList<Student>();
        try {
            String SQL = "SELECT * FROM student WHERE address like ? ";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, "%" + address + "%");
            ResultSet resultSet = statement.executeQuery();
            LuuTru luuTru = new LuuTru();
            List<Student> listok = luuTru.listThu(resultSet);
            return listok;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Student> increaserAge() {
        if (con != null) {
            String SQL = "SELECT * FROM student ORDER BY age";
            try {
                PreparedStatement statement = con.prepareStatement(SQL);
                ResultSet resultSet = statement.executeQuery();
                LuuTru luuTru = new LuuTru();
                List<Student> list = luuTru.listThu(resultSet);
                if (list.size() == 0) {
                    System.out.println("Không tìm thấy kết quả");
                }
                return list;
            } catch (SQLException e) {
                System.out.println("Lỗi truy vấn câu lệnh");
            }

        } else {
            System.out.println("Không có kết nối với ConnectionPostgres");
        }
        return null;
    }

    @Override
    public List<Student> decreaserAge() {
        if (con != null) {
            String SQL = "SELECT * FROM student ORDER BY age DESC";
            try {
                PreparedStatement statement = con.prepareStatement(SQL);
                ResultSet resultSet = statement.executeQuery();
                LuuTru luuTru = new LuuTru();
                List<Student> list = luuTru.listThu(resultSet);
                if (list.size() == 0) {
                    System.out.println("Không tìm thấy kết quả");
                }
                return list;
            } catch (SQLException e) {
                System.out.println("Lỗi truy vấn câu lệnh");
            }

        } else {
            System.out.println("Không có kết nối với ConnectionPostgres");
        }
        return null;
    }

    @Override
    public List<Student> selectByNameOrByAddress(String str) {
        if (con != null) {
            String SQL = "SELECT * FROM student WHERE address like ? OR name like ?";
            try {
                PreparedStatement statement = con.prepareStatement(SQL);
                statement.setString(1, "%" + str + "%");
                statement.setString(2, "%" + str + "%");
                ResultSet resultSet = statement.executeQuery();
                LuuTru luuTru = new LuuTru();
                List<Student> list = luuTru.listThu(resultSet);
                return list;
            } catch (SQLException e) {
                System.out.println("Lỗi truy vấn câu lệnh");
            }

        } else {
            System.out.println("Không có kết nối với ConnectionPostgres");
        }
        return null;
    }

    @Override
    public List<Student> selectDeletedAll() {
        if (con != null) {
            String SQL = "SELECT * FROM student";
            LuuTru luuTru = new LuuTru();
            List<Student> studentList = new ArrayList<Student>();
            List<Student> list = luuTru.listDeleteStudent(SQL);
            for (Student students : list) {
                if (students.getDeleted_at() != null) {
                    studentList.add(students);
                }
            }
            return studentList;
        } else {
            System.out.println("Không có kết nối với ConnectionPostgres");
        }
        return null;
    }


}
