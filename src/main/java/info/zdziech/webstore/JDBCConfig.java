package info.zdziech.webstore;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "info.zdziech")
public class JDBCConfig {
    @Bean
    public DataSource dataSource () throws SQLException {
        OracleDataSource dataSource =  new OracleDataSource () ;
    try {
            dataSource = new OracleDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dataSource.setDatabaseName("SKLEP");
        dataSource.setURL("jdbc:oracle:thin:@127.0.0.1:1521:xe");
        dataSource.setUser("sklep");
        dataSource.setPassword("123");
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(dataSource());
    }


}
