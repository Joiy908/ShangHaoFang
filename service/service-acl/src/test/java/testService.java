import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@SpringJUnitConfig(locations = {
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
})
public class testService {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
    }
//
//    @Autowired
//    private AdminService adminService;
//
//    @Test
//    public void testGet() {
//        final Admin byId = adminService.getById(1L);
//        System.out.println("byId = " + byId);
//    }
}
