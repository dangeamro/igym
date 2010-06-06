package com.designer.common.framework;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;


public class ApplicationDataSource {
	
	private static ApplicationDataSource instance = null;
	private BasicDataSource connectionFactory = null;
	
	private ApplicationDataSource() {
		connectionFactory = new BasicDataSource();
		ApplicationConfig config = ApplicationConfig.getInstance();
		connectionFactory.setDriverClassName(config.getConfigValue("db.driver.class"));
		connectionFactory.setUrl(config.getConfigValue("db.connection.url"));
		connectionFactory.setUsername(config.getConfigValue("db.user.name"));
		connectionFactory.setPassword(config.getConfigValue("db.password"));

		connectionFactory.setInitialSize(Integer.parseInt(config.getConfigValue("conn.pool.initial.size")));
		connectionFactory.setMaxActive(Integer.parseInt(config.getConfigValue("conn.pool.max.active")));
		connectionFactory.setMaxIdle(Integer.parseInt(config.getConfigValue("conn.pool.max.idle")));
	}
	
	public static synchronized ApplicationDataSource getInstance() {
		if(instance == null){
			instance = new ApplicationDataSource();
		}
		
		return instance;
	}
	
	public BasicDataSource getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(BasicDataSource connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	public Connection getConnection() throws SQLException{
		return this.connectionFactory.getConnection();
	}
}
