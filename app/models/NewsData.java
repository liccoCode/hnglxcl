package models;

import helper.Constant;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: licco
 * Date: 2018/9/6
 * Time: 下午2:23
 */
@Entity
public class NewsData extends Model {

    public String title;

    public String source;

    @Enumerated(EnumType.STRING)
    public T type;

    public enum T {
        Industry {
            @Override
            public String label() {
                return "行业新闻";
            }
        },
        Company {
            @Override
            public String label() {
                return "公司新闻";
            }
        };

        public abstract String label();
    }

    public String href;

    public Date createDate;

    public String outline;

    public String content;

    public String location(String fileName) {
        return String.format("%s/%s/%s", Constant.UPLOAD_PATH, type, fileName);
    }

}
