package DAO;


import Entities.student;

import java.sql.SQLException;
import java.util.List;

public interface IRankLevelsDao {
    public List<student> selectAll() throws SQLException;

    public Boolean insert(student rl);

    public Boolean update(student rl);

    public Boolean delete(int idxoa);

    public student selectByID(int idtim);

    public student selectByCode(String macode);

    public List<student> selectByName(String name);

    public List<student> selectByAddress(String address);

    public List<student> increaserAge();

    public List<student> decreaserAge();

    public List<student> selectByNameOrByAddress(String str);

}
