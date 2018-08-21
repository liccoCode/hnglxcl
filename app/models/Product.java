package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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


}
