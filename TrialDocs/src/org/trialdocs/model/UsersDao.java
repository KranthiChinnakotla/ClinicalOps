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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Component("usersDao")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public UsersDao() {
		System.out.println("The J2EE bean is added!");
	}

	public boolean createUser(Users users) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(users);
		return jdbc.update(
				"insert into cops_users (name,email,password,role,status) values (:username, :email, :password, :role,"+0+")",
				params) == 1;
	}

	public Users getUser(String email, String pass) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		params.addValue("password", pass);
		try {
			Users user = jdbc.queryForObject("select * from cops_users where email = :email and password = :password",
					params, new RowMapper<Users>() {

						@Override
						public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
							// TODO Auto-generated method stub

							Users user = new Users();
							user.setEmail(rs.getString("email"));
							user.setUsername(rs.getString("name"));
							user.setRole(rs.getString("role"));
							return user;
						}

					});
			return user;
		} catch (Exception e) {

			return null;
		}

	}	
}
