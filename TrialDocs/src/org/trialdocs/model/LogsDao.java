package org.trialdocs.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("logsDao")
public class LogsDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc){		
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);		
	}
	
	public boolean createLogs(Logs logs){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(logs);
		return jdbc.update("insert into log_users (email,role,message,date) values (:email,:role,:message,:date)", params) == 1;
	}
	
	public List<Logs> getLogsE(String email){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		try{
			return jdbc.query("select * from log_users where email = :email", params, new RowMapper<Logs>(){

				@Override
				public Logs mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					return new Logs(rs.getString("email"),rs.getString("role"),rs.getString("message"),rs.getString("date"));
					
				}
				
			});
		}catch(Exception e){
			
			System.out.println("Exception thrown: "+e.getMessage());
			return null;
		}
	}
	
	public List<Logs> getLogsD(String date){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("date", date);
		
		try{
			return jdbc.query("select * from log_users where date = :date", params, new RowMapper<Logs>(){

				@Override
				public Logs mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					
					return new Logs(rs.getString("email"),rs.getString("role"),rs.getString("message"),rs.getString("date"));
				    
				}
				
			});
		}catch(Exception e){
			
			System.out.println("Exception thrown: "+e.getMessage());
			return null;
			
		}
		
		
	}
	
	public List<Logs> getLogsR(String role){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("role", role);
		
		try{
			return jdbc.query("select * from log_users where role = :role", params, new RowMapper<Logs>(){

				@Override
				public Logs mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					
					return new Logs(rs.getString("email"),rs.getString("role"),rs.getString("message"),rs.getString("date"));
				    
				}
				
			});
		}catch(Exception e){
			
			System.out.println("Exception thrown: "+e.getMessage());
			return null;
			
		}
		
		
	}
} 
