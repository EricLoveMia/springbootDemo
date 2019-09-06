package cn.eric.springbootdemo.controller;

import cn.eric.springbootdemo.controller.dto.R;
import cn.eric.springbootdemo.dao.PhoneModelRepository;
import cn.eric.springbootdemo.domain.ColorModeBean;
import cn.eric.springbootdemo.domain.HuaWeiPhoneBean;
import cn.eric.springbootdemo.domain.PhoneModel;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: HttpController
 * @Description: TODO
 * @company lsj
 * @date 2019/7/2 11:18
 **/
@RestController
@RequestMapping("/http")
public class HttpController {

    @Resource
    private PhoneModelRepository phoneModelRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/huawei")
    public R huawei() throws IOException {
        // 创建httpclient实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpget实例
        HttpGet httpget = new HttpGet("https://consumer.huawei.com/cn/phones/?ic_medium=hwdc&ic_source=corp_header_consumer");
        // 执行get请求
        CloseableHttpResponse response = httpclient.execute(httpget);
        // 获取返回实体
        HttpEntity entity=response.getEntity();
        // 指定编码打印网页内容
        // System.out.println("网页内容："+ EntityUtils.toString(entity, "utf-8"));
        String content = EntityUtils.toString(entity, "utf-8");

        System.out.println(content);

        Document document = Jsoup.parse(content);
        Elements elements = document.select("#content-v3-plp #pagehidedata .plphidedata");
        for (Element element : elements) {
            //System.out.println(element.text());
            String jsonStr = element.text();
            System.out.println(jsonStr);
            List<HuaWeiPhoneBean> list = JSON.parseArray(jsonStr, HuaWeiPhoneBean.class);
            for (HuaWeiPhoneBean bean : list) {
                String productName = bean.getProductName();
                List<ColorModeBean> colorsItemModeList = bean.getColorsItemMode();

                StringBuilder colors = new StringBuilder();
                for (ColorModeBean colorModeBean : colorsItemModeList) {
                    String colorName = colorModeBean.getColorName();
                    colors.append(colorName).append(";");
                }

                List<String> sellingPointList = bean.getSellingPoints();
                StringBuilder sellingPoints = new StringBuilder();
                for (String sellingPoint : sellingPointList) {
                    sellingPoints.append(sellingPoint).append(";");
                }

//                System.out.println("产品名：" + productName);
//                System.out.println("颜  色：" + color);
//                System.out.println("买  点：" + sellingPoint);
//                System.out.println("-----------------------------------");
                PhoneModel phoneModel = new PhoneModel()
                        .setName(productName)
                        .setColors(colors.substring(0, colors.length() - 1))
                        .setSellingPoints(sellingPoints.substring(0, sellingPoints.length() - 1))
                        .setCreateTime(new Date());
                phoneModelRepository.save(phoneModel);
            }
        }
        httpclient.close();
        response.close(); // 关闭流和释放系统资源
        return R.ok();
    }


}
