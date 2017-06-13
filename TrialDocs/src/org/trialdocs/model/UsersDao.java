package org.trialdocs.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Component("usersDao")
public class UsersDao {

  private NamedParameterJdbcTemplate jdbc;
	
  @Autowired
  public void serDataSource(DataSource jdbc){
	  this.jdbc = new NamedParameterJdbcTemplate(jdbc);
  }
	
  public UsersDao(){
	  System.out.println("The J2EE bean is added!");
  }
  
  public boolean createUser(Users users){
	  
	  BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(users);
	  return jdbc.update("insert into cops_users (name,email,password,role) values (:username, :email, :password, :role)", params) == 1;
  }
}
