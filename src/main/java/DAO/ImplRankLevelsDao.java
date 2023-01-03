package DAO;

import Connection_DB.ConnectionPostgres;
import Entities.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplRankLevelsDao implements IRankLevelsDao {

    private Connection con;

    public ImplRankLevelsDao() {
        con = ConnectionPostgres.getConnection();
    }

    @Override
    public List<student> selectAll() {
        if (con != null) {
            String SQL = "SELECT * FROM student";
            LuuTru luuTru = new LuuTru();
            List<student> list = luuTru.listStudent(SQL);
            return list;
        } else {
            System.out.println("Không có kết nối với ConnectionPostgres");
        }
        return null;
    }


    @Override
    public Boolean insert(student std) {
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
    public Boolean update(student std) {
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
                String SQL = "DELETE FROM student WHERE id = ?";
                PreparedStatement preparedStatement = con.prepareStatement(SQL);
                preparedStatement.setInt(1, idxoa);
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
    public student selectByID(int idtim) {
        try {
            String SQL = "SELECT * FROM student WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setInt(1, idtim);
            ResultSet resultSet = statement.executeQuery();
//            rank_levels rankLevels = new rank_levels();
            LuuTru luuTru = new LuuTru();
            student studentok = luuTru.getStudent(resultSet);
            return studentok;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public student selectByCode(String macode) {
        try {
            String SQL = "SELECT * FROM student WHERE code = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, macode);
            ResultSet resultSet = statement.executeQuery();
            LuuTru luuTru = new LuuTru();
            student studentpl = luuTru.getStudent(resultSet);
            return studentpl;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<student> selectByName(String name) {
        try {
            String SQL = "SELECT * FROM student WHERE name like ? ";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
//            rank_levels rankLevels = new rank_levels();
            LuuTru luuTru = new LuuTru();
            List<student> list = luuTru.listThu(resultSet);
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<student> selectByAddress(String address) {
        List<student> list = new ArrayList<student>();
        try {
            String SQL = "SELECT * FROM student WHERE address like ? ";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setString(1, "%" + address + "%");
            ResultSet resultSet = statement.executeQuery();
            LuuTru luuTru = new LuuTru();
            List<student> listok = luuTru.listThu(resultSet);
            return listok;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<student> increaserAge() {
        if (con != null) {
            String SQL = "SELECT * FROM student ORDER BY age";
            try {
                PreparedStatement statement = con.prepareStatement(SQL);
                ResultSet resultSet = statement.executeQuery();
                LuuTru luuTru = new LuuTru();
                List<student> list = luuTru.listThu(resultSet);
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
    public List<student> decreaserAge() {
        if (con != null) {
            String SQL = "SELECT * FROM student ORDER BY age DESC";
            try {
                PreparedStatement statement = con.prepareStatement(SQL);
                ResultSet resultSet = statement.executeQuery();
                LuuTru luuTru = new LuuTru();
                List<student> list = luuTru.listThu(resultSet);
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
    public List<student> selectByNameOrByAddress(String str) {
        if (con != null) {
            String SQL = "SELECT * FROM student WHERE address like ? OR name like ?";
            try {
                PreparedStatement statement = con.prepareStatement(SQL);
                statement.setString(1, "%" + str + "%");
                statement.setString(2, "%" + str + "%");
                ResultSet resultSet = statement.executeQuery();
                LuuTru luuTru = new LuuTru();
                List<student> list = luuTru.listThu(resultSet);
                return list;
            } catch (SQLException e) {
                System.out.println("Lỗi truy vấn câu lệnh");
            }

        } else {
            System.out.println("Không có kết nối với ConnectionPostgres");
        }
        return null;
    }


}
