package DAO;


import Entities.Student;

import java.sql.SQLException;
import java.util.List;

public interface IRankLevelsDao {
    public List<Student> selectAll() throws SQLException;

    public Boolean insert(Student rl);

    public Boolean update(Student rl);

    public Boolean delete(int idxoa);

    public Student selectByID(int idtim);

    public Student selectByCode(String macode);

    public List<Student> selectByName(String name);

    public List<Student> selectByAddress(String address);

    public List<Student> increaserAge();

    public List<Student> decreaserAge();

    public List<Student> selectByNameOrByAddress(String str);


    public List<Student> selectDeletedAll();
}
