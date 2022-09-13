import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.api.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Joiy908
 * @date 2022/9/13
 */

@SpringJUnitConfig(locations = {"classpath:spring/spring-dao.xml"})
public class testConn {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
    }

    @Autowired
    private RoleDao roleDao;

    @Test
    public void testFindAll() {
        final List<Role> all = roleDao.findAll();
        for (Role role : all) {
            System.out.println("role = " + role);
        }
    }
}
