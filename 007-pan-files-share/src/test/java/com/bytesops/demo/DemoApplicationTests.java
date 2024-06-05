package com.bytesops.demo;

import com.bytesops.demo.orm.PanFile;
import com.bytesops.demo.orm.PanFileDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private PanFileDao panFileDao;

    @Test
    void contextLoads() {
        List<PanFile> list = panFileDao.findAll();
        list.forEach(panFile -> {
            System.out.println(panFile.getFileId());
        });
    }

}
