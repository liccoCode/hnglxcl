package controllers;

import models.Product;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: licco
 * Date: 2018/9/5
 * Time: 下午9:04
 */
@With({Secure.class})
public class Systems extends Controller {

    public static void productIndex(){
        List<Product> products = Product.findAll();
        render(products);
    }

    public static void addProduct(){
        render();
    }


}
