package org.kee.vue.server.vhr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    static void print(String str) {
        System.out.println(str + ":" + localVariable.get());
    }

    @Test
    void threadLocalTest() {
        // 创建线程1
        Thread t1 = new Thread(() -> {
            localVariable.set("t1 local variable");
            print("t1");
            System.out.println("t1 remove after :" + localVariable.get());
        });
        // 创建线程2
        Thread t2 = new Thread(() -> {
            localVariable.set("t2 local variable");
            print("t2");
            System.out.println("t2 remove after :" + localVariable.get());
        });

        t1.start();
        t2.start();
    }

}
