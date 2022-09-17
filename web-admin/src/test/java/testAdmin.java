import com.atguigu.entity.Admin;
import com.atguigu.service.api.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(locations = {"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class testAdmin {
    @Autowired
    private AdminService adminService;

    @Test
    public void testGet() {
        final Admin byId = adminService.getById(1L);
        System.out.println("byId = " + byId);
    }
}
