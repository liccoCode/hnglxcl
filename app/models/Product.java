package models;

import helper.Constant;
import play.db.jpa.Model;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: licco
 * Date: 2018/8/21
 * Time: 下午8:49
 */
@Entity
public class Product extends Model {

    public String name;

    public enum T {
        HYJ {
            @Override
            public String label() {
                return "环氧基材料";
            }
        },
        GXNSN {
            @Override
            public String label() {
                return "高性能水泥基材料";
            }
        },
        JHWGXSN {
            @Override
            public String label() {
                return "聚合物改性水泥基材料";
            }
        },
        CGXNHNT {
            @Override
            public String label() {
                return "超高性能混凝土材料";
            }
        };

        public abstract String label();
    }

    @Enumerated(EnumType.STRING)
    public T type;

    public String href;

    public String description;

    public String feature;

    public String application;

    @Transient
    public File file;

    public String pdf;

    @ManyToOne
    public User creator;

    public Date createDate;

    public String location(String fileName) {
        return String.format("%s/%s/%s", Constant.UPLOAD_PATH, type, fileName);
    }
}
