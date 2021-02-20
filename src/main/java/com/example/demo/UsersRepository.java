package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.dao.DataAccessException;

@Repository
public class UsersRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UsersRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Users selectOne(Long id) throws DataAccessException {
        // SQL文を作成
        String sql = ""
            + "SELECT"
                + " *"
            + " FROM"
                + " users"
            + " WHERE"
                + " id = ?";
        Map<String, Object> users = jdbcTemplate.queryForMap(sql, id);

        // Userオブジェクトに格納する。
        Users user = new Users();
        user.setId((Integer)users.get("id"));
        user.setName((String)users.get("name"));
		user.setDepartment((String)users.get("department"));
        return user;
    }
	
	public Users delete(Long id) throws DataAccessException {
        String sql1 = ""
            + "SELECT * FROM users WHERE id = ?";
        Map<String, Object> users = jdbcTemplate.queryForMap(sql1, id);
        Users user = new Users();
        user.setName((String)users.get("name"));
		user.setDepartment((String)users.get("department"));
		
		String sql2 = "DELETE FROM users WHERE id = ?";
	    jdbcTemplate.update(sql2, id);
        return user;
    }
	
	public Users update(Users users) throws DataAccessException {
        // SQL文を作成
        String sql = ""
            + "UPDATE users SET name = ?, department = ?"
            + " WHERE"  + " id = ?";
        jdbcTemplate.update(sql, users.getName(),users.getDepartment(),users.getId());
        return users;
    }
	
	public void insertUsers(Users users) {
		jdbcTemplate.update("INSERT INTO users(name,department) Values (?,?)",
				users.getName(), users.getDepartment());
	}
	
	public List<Users> getAll(){
		String sql = "select id, name, department from users";
		List<Map<String, Object>>usersList = jdbcTemplate.queryForList(sql);
		List<Users> list = new ArrayList<>();
		for(Map<String, Object> str1: usersList) {
			Users users = new Users();
			users.setId((int)str1.get("id"));
			users.setName((String)str1.get("name"));
			users.setDepartment((String)str1.get("department"));
			list.add(users);
		}
		return list;
	}
	

}