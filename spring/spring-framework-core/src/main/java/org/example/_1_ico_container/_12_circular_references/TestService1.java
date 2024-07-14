package org.example._1_ico_container._12_circular_references;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService1 {

    @Autowired
    private TestService2 testService2;

    public TestService1() {
        // debug
        System.out.println();
    }

    public void test1() {
    }
}
