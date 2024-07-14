package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class TransactionTest {
    @Autowired
    private StaffService staffService;

    @Before
    public void init() {
        staffService.deleteAll();
    }

    @Test
    public void transactionTest() {
        Staff staff = new Staff();
        staff.setName("1");

        Staff staff2 = new Staff();
        staff2.setName("JDBCTemplate123456789");

        List<Staff> staffs = new ArrayList<>();
        staffs.add(staff);
        staffs.add(staff2);

        staffService.add(staffs);

        Assert.assertTrue(staffService.list().isEmpty());
    }

    @Test
    public void noTransactionTest() {
        Staff staff = new Staff();
        staff.setName("1");

        Staff staff2 = new Staff();
        staff2.setName("JDBCTemplate123456789");

        List<Staff> staffs = new ArrayList<>();
        staffs.add(staff);
        staffs.add(staff2);

        staffService.addNoTransaction(staffs);

        Assert.assertTrue(staffService.list().isEmpty());
    }

}
