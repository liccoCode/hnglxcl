package controllers;

import models.NewsData;
import models.Product;
import org.apache.commons.io.FileUtils;
import play.mvc.Controller;
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
 * Time: 下午2:12
 */
public class News extends Controller {

    public static void companyNewsIndex() {
        List<NewsData> newsDataList = NewsData.findAll();
        newsDataList.forEach(newsData -> {
            newsData.outline = newsData.outline.replaceAll("<br />", "");
        });
        render(newsDataList);
    }

    public static void projectIndex() {
        render();
    }

    public static void applicationIndex() {
        render();
    }

    public static void bbgPage() {
        render();
    }

    public static void xiangYaPage() {
        render();
    }

    public static void cstPage() {
        render();
    }

    public static void dthPage() {
        render();
    }

    public static void azPage() {
        render();
    }

    public static void njPage() {
        render();
    }

    public static void show(Long id) {
        NewsData n = NewsData.findById(id);
        n.content = n.content.replaceAll("<br />", "");
        render(n);
    }

    public static void createNews(NewsData n) {
        n.createDate = new Date();
        n.save();
        Systems.newsIndex();
    }

    public static void addPic(Long id, String type, File uploadFile) {
        NewsData newsData = NewsData.findById(id);
        String path = newsData.location(uploadFile.getName());
        try {
            FileUtils.copyFile(uploadFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        newsData.href = path;
        newsData.save();
        Systems.newsIndex();
    }

    public static void image(Long id) {
        NewsData newsData = NewsData.findById(id);
        if (newsData != null) {
            File file = new File(newsData.href);
            renderBinary(file);
        } else {
            throw new FastRuntimeException("No File Found.");
        }
    }

    public static void edit(Long id) {
        NewsData n = NewsData.findById(id);
        render("/Systems/addNews.html", n);
    }
}
