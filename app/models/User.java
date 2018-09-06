package models;

import com.google.gson.annotations.Expose;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;
import play.mvc.Scope;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by IntelliJ IDEA.
 * User: licco
 * Date: 2018/9/5
 * Time: 下午8:56
 */
@Entity
public class User extends Model {

    @Column(nullable = false, unique = true)
    @Required
    @Expose
    public String username;

    /**
     * 加密以后的密码
     */
    @Required
    @Password
    public String passwordDigest;

    @Transient
    public String password;


    public static User current() {
        return User.findByUserName(username());
    }

    public static User findByUserName(String username) {
        return User.find("username=?", username).first();
    }

    /**
     * 当前登陆用户的名称
     *
     * @return
     */
    public static String username() {
        String username = Scope.Session.current().get("username");
        if (StringUtils.isBlank(username)) return "system";
        else return StringUtils.lowerCase(username);
    }

    /**
     * 验证用户登陆
     *
     * @param password
     * @return
     */
    public boolean authenticate(String password) {
        return !StringUtils.isBlank(this.passwordDigest) && this.passwordDigest.equals(Crypto.encryptAES(password));
    }

    /**
     * 用户登陆后将验证key值返回给 cookie
     *
     * @param username
     * @return
     */
    public static String userMd5(String username) {
        return User.md5(String.format("playelcuk2userauthenticate%s", username));
    }

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }
}
