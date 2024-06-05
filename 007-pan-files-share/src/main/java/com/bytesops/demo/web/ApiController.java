package com.bytesops.demo.web;

import com.bytesops.demo.orm.PanFile;
import com.bytesops.demo.orm.PanFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PanFileDao panFileDao;

    @PostMapping("/file")
    public String saveFile(@RequestBody PanFile file) {
        panFileDao.save(file);
        return "success";
    }
}
