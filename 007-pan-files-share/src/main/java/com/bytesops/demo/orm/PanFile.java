package com.bytesops.demo.orm;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pan_file")
@Data
public class PanFile implements Serializable {

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileId;
    private String fileName;
    private Boolean dir;
    private String parentFileId;
    private String path;
    private Date createTime;
    private Date updateTime;

}
