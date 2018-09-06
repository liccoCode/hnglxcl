package controllers;

import models.Product;
import org.apache.commons.io.FileUtils;
import play.mvc.Controller;
import play.mvc.Http;
import play.utils.FastRuntimeException;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: licco
 * Date: 2018/7/12
 * Time: 下午2:44
 */
public class Products extends Controller {

    public static void productIndex() {
        List<Product> hyjList = Product.find("type=?", Product.T.HYJ).fetch();
        List<Product> gxnsnList = Product.find("type=?", Product.T.GXNSN).fetch();
        List<Product> jhwgxsnList = Product.find("type=?", Product.T.JHWGXSN).fetch();
        List<Product> cgxnhntList = Product.find("type=?", Product.T.CGXNHNT).fetch();
        render(hyjList, gxnsnList, jhwgxsnList, cgxnhntList);
    }

    public static void serviceIndex() {
        render();
    }

    public static void show(Long id) {
        Product product = Product.findById(id);
        product.feature = product.feature.replaceAll("<br />", "");
        product.application = product.feature.replaceAll("<br />", "");
        product.description = product.feature.replaceAll("<br />", "");
        render(product);
    }

    public static void downloadPDF(Long id) {
        Product product = Product.findById(id);
        File pdf = new File(product.pdf);
        renderBinary(pdf);
    }

    public static void createProduct(Product p) {
        p.createDate = new Date();
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
            product.pdfName = uploadFile.getName();
        } else {
            product.href = path;
        }

        product.save();
        Systems.productIndex();
    }

    public static void image(Long id) {
        Product product = Product.findById(id);
        if (product != null) {
            File file = new File(product.href);
            renderBinary(file);
        } else {
            throw new FastRuntimeException("No File Found.");
        }
    }

    public static void edit(Long id) {
        Product p = Product.findById(id);
        render("/Systems/addProduct.html", p);
    }

}
