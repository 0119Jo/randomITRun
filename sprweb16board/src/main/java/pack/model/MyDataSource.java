package pack.model;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

//@Repository("dataSource")
public class MyDataSource extends DriverManagerDataSource {
	public MyDataSource() {
		setDriverClassName("org.mariadb.jdbc.Driver");
		setUrl("jdbc:mysql://127.0.0.1:3306/happydb");
		setUsername("root");
		setPassword("123");
	}
}