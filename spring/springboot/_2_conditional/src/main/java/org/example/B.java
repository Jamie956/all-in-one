package org.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
// create B if exist bean name a
@ConditionalOnBean(name = "a")
// create B if not exist bean name a1
@ConditionalOnMissingBean(name = "a1")
// create B if exist class A
@ConditionalOnClass(value = {A.class})
public class B {
}
