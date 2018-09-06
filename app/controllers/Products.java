package controllers;

import models.Product;
import org.apache.commons.io.FileUtils;
import play.mvc.Controller;
import play.mvc.Http;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: licco
 * Date: 2018/7/12
 * Time: 下午2:44
 */
public class Products extends Controller {

    public static void productIndex() {
        render();
    }

    public static void serviceIndex() {
        render();
    }

    public static void show() {
        Product product = Product.findById(1l);
        render(product);
    }

    public static void downloadPDF() {
        render();
    }

    public static void createProduct(Product p) {
        p.createDate = new Date();
        p.feature = p.feature.replaceAll("<br />", "");
        p.application = p.application.replaceAll("<br />", "");
        p.save();
        Systems.productIndex();
    }

    public static void addPDF(Long id, String type, File uploadFile) {
        Product product = Product.findById(id);
        String path = product.location(uploadFile.getName());
        try {
            FileUtils.copyFile(uploadFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.equals("pdf", type)) {
            product.pdf = path;
        } else {
            product.href = path;
        }
        product.save();
        Systems.productIndex();
    }

}
