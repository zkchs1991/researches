package com.zk.sbt.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by zk_chs on 2/3/18.
 */
@RestController
@RequestMapping(value = {"/"})
public class BankController {

    private static final Logger log = LogManager.getLogger();

    private static final String REQ_GET = "GET";
    private static final String REQ_POST = "POST";

    private static final String ICBC_HOST = "https://mybank.icbc.com.cn";
    private static final String ICBC_INDEX_URL = "https://mybank.icbc.com.cn/icbc/newperbank/perbank3/frame/frame_index.jsp";

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

//    @RequestMapping(value = "/icbc")
//    public void icbc () throws IOException {
//        Response resp = Request.Get(ICBC_INDEX_URL)
//                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//                .addHeader("Accept-Encoding", "gzip, deflate, br")
//                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
//                .addHeader("Cache-Control", "max-age=0")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Host", "mybank.icbc.com.cn")
//                .addHeader("Referer", "https://mybank.icbc.com.cn/icbc/newperbank/perbank3/frame/frame_guide_nologon.jsp")
//                .addHeader("Upgrade-Insecure-Requests", "1")
//                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
//                .addHeader("Cookie", "first_tip=0; isP3bank=1; isEn_US=0; isPri=; guide_nologon=Sun, 03 Feb 2019 09:23:00 GMT; firstZoneNo=%E5%8C%97%E4%BA%AC_0200; BIGipServergeren_80_pool=221442058.20480.0000; area_1002=1002")
//                .execute();
//
//        String page = resp.returnContent().asString(Charset.forName("GBK"));
//        response.setContentType("text/html;charset=GBK");
//        response.getWriter().write(page);
//    }

    @RequestMapping(value = "/**")
    public void icbc () throws IOException {
        String ICBC_REQ_URL = ICBC_HOST + request.getRequestURI();
        String ICBC_REQ_METHOD = request.getMethod();

        /** 处理GET请求 */
        if (StringUtils.equalsIgnoreCase(ICBC_REQ_METHOD, REQ_GET)){
            Response ICBC_RESP = Request.Get(ICBC_REQ_URL)
                    .setHeaders(parseHeaders(request))
                    .execute();
            HttpResponse ICBC_HTTP_RESP = ICBC_RESP.returnResponse();
            /** headers 处理 */
            List<Header> RESP_HEADERS = Arrays.asList(ICBC_HTTP_RESP.getAllHeaders());
            RESP_HEADERS.forEach(header -> response.setHeader(header.getName(), header.getValue()));
            /** entity 处理 */
            ICBC_HTTP_RESP.getEntity().writeTo(response.getOutputStream());
        }

        if (StringUtils.equalsIgnoreCase(ICBC_REQ_METHOD, REQ_POST)){
            Response ICBC_RESP = Request.Post(ICBC_REQ_URL)
                    .setHeaders(parseHeaders(request))
                    .bodyForm(parsePostParams(request))
                    .bodyForm()
                    .execute();
            HttpResponse ICBC_HTTP_RESP = ICBC_RESP.returnResponse();
            /** headers 处理 */
            List<Header> RESP_HEADERS = Arrays.asList(ICBC_HTTP_RESP.getAllHeaders());
            RESP_HEADERS.forEach(header -> response.setHeader(header.getName(), header.getValue()));
            /** entity 处理 */
            ICBC_HTTP_RESP.getEntity().writeTo(response.getOutputStream());
        }

    }

    private Header[] parseHeaders (HttpServletRequest request){
        List<Header> headers = new ArrayList<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            if (key.equalsIgnoreCase("host")){
                value = "mybank.icbc.com.cn";
            }
            if (key.equalsIgnoreCase("referer")){
                value = value.replace("http://127.0.0.1:8080", ICBC_HOST);
            }
            if (key.equalsIgnoreCase("origin")){
                value = ICBC_HOST;
            }
            if (key.equalsIgnoreCase("content-length") && request.getMethod().equalsIgnoreCase(REQ_POST)){
                continue;
            }
            Header header = new BasicHeader(key, value);
            headers.add(header);
        }
        return headers.toArray(new Header[0]);
    }

    private List<NameValuePair> parsePostParams (HttpServletRequest request){
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = request.getParameter(key);
            System.out.println("post param -> key: " + key + "     value: " + value);
            NameValuePair nameValuePair = new BasicNameValuePair(key, value);
            nameValuePairs.add(nameValuePair);
        }
        return nameValuePairs;
    }

}
