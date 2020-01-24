package com.kisanmitra.connection;

import org.springframework.jdbc.core.JdbcTemplate;

public class MyConnection {
	private static JdbcTemplate jdbcTemplate;

	
	
	public MyConnection() {
		super();
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
}
