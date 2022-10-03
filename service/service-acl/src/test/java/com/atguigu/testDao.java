package com.atguigu;

import com.atguigu.dao.AdminDao;
import com.atguigu.dao.AdminRoleDao;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@SpringJUnitConfig(locations = {
        "classpath:spring/spring-dao.xml",
})
public class testDao {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
    }


    @Autowired
    private AdminDao adminDao;

    @Test
    public void testFindAll() {
        final List<Admin> all = adminDao.findAll();
    }

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Test
    public void testAdminRoleDao() {
        adminRoleDao.findRoleIdByAdminId(1L);
    }
}
