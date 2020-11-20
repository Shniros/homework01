package ru.Shniros.DAO.Impl;

import ru.Shniros.DAO.iDao;
import ru.Shniros.DAO.jdbc.SingleConnectionManager;
import ru.Shniros.DAO.domain.Person;

import java.sql.*;
import java.util.List;

public class PersonDAO implements iDao<Person, Integer> {
    private String TABLE_NAME = "finance.person";
    private static Connection connection;

    @Override
    public Person findById(Integer integer) {
        return null;
    }

    @Override
    public List<Person> findByAll() {
        return null;
    }

    @Override
    public Person insert(Person person){

        try{
            connection = SingleConnectionManager.getConnection();
            String insertSQL = "INSERT INTO " + TABLE_NAME +
                    "(first_name, " +
                    "last_name, " +
                    "email, " +
                    "password, " +
                    " VALUES(?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(insertSQL);
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getEmail());
            ps.setString(4, person.getPassword());
            ps.executeUpdate();
            return person;

        }catch (SQLException throwables){
            throwables.getStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException conEX) {
                conEX.printStackTrace();
            }
        }
        return null;
    }
    public Person findByEmail(String email) {
        try {
            connection = SingleConnectionManager.getConnection();
            String query = "SELECT * FROM " + TABLE_NAME
                    + " WHERE email = ?;";
            PreparedStatement p_stmt = connection.prepareStatement(query);
            p_stmt.setString(1,email);
            ResultSet rs = p_stmt.executeQuery();
            if(rs.next()){
                Person findPerson = new Person();
                findPerson.setId(rs.getInt("id"));
                findPerson.setFirstName(rs.getString("first_name"));
                findPerson.setLastName(rs.getString("last_name"));
                findPerson.setEmail(rs.getString("email"));
                findPerson.setPassword(rs.getString("password"));
                return findPerson;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException conEx) {
                conEx.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public Person update(Person person) {
        try {
            connection = SingleConnectionManager.getConnection();
            String query = "UPDATE " + TABLE_NAME +
                    " SET first_name = ?," +
                    "last_name = ?," +
                    "email = ?," +
                    "password = ?" +
                    " WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,person.getFirstName());
            ps.setString(2,person.getLastName());
            ps.setString(3,person.getEmail());
            ps.setString(4,person.getPassword());
            ps.setInt(5,person.getId());
            ps.execute();
            return person;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException conEx) {
                conEx.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        //TODO
        return false;
    }
}
