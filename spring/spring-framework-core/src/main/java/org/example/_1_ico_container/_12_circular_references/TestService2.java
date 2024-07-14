package org.example._1_ico_container._12_circular_references;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TestService2 {

    @Autowired
    private TestService1 testService1;

    public TestService2() {
        // debug
        System.out.println();
    }

    public void test2() {
    }
}
