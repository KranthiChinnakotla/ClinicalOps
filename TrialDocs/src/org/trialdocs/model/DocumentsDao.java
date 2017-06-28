package org.trialdocs.model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("documentDao")
public class DocumentsDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public boolean createDocument(Documents docs) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(docs);
		return jdbc.update("insert into file_upload (email,content,filename,content_type) values (:email,:inputStream,:fileName,:contenttype)",
				params) == 1;

	}
	
	public boolean deleteDocument(String fileName){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("fileName", fileName);
		return jdbc.update("delete from file_upload where filename = :fileName", params) == 1;
		
	}

	public List<Documents> getDocuments(String email) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		// List<Documents> docs = new ArrayList<Documents>();

		try {

			return jdbc.query("select * from file_upload where email= :email", new RowMapper<Documents>() {

				@Override
				public Documents mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub

					Blob blob = rs.getBlob("content");
					Documents document = new Documents(rs.getString("email"), blob.getBinaryStream(),
							rs.getString("filename"),rs.getString("content_type"));
					return document;
				}

			});

		} catch (Exception e) {
			return null;
		}
	}
	
	
	public Documents getSingleDocument(String filename){
	
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("filename", filename);
		
		try{
			return jdbc.queryForObject("select * from file_upload where filename = :filename", params, new RowMapper<Documents>(){

				@Override
				public Documents mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					
					Blob blob = rs.getBlob("content");
					Documents document = new Documents(rs.getString("email"),blob.getBinaryStream(),rs.getString("filename"),rs.getString("content_type"));
					return document;
				}		
			});
		}catch(Exception e){
			return null;
		}
	}
}
