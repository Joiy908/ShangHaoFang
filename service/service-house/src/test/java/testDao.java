import com.atguigu.dao.DictDao;
import com.atguigu.entity.Dict;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @author Joiy908
 * @date 2022/9/20
 */

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-dao.xml"
})
public class testDao {
    @Autowired
    private DictDao dictDao;

    @Test
    public void testGet() {
        List<Dict> listByParentId = dictDao.findListByParentId(1L);
        System.out.println("listByParentId = " + listByParentId);
    }
}
