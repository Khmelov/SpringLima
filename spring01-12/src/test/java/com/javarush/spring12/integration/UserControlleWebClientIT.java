package com.javarush.spring12.integration;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@TestHtmlAndJsonContext
@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControlleWebClientIT {

    private final WebApplicationContext wac;

    private WebClient webClient;

    @BeforeEach
    public void setup() {
        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(wac).build();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
    }

    @Test
    void indexHtml() throws IOException {
        Page page = webClient.getPage("http://localhost:8080/");
        String xpathExpr = "/html/body/div/h2";
        HtmlElement userH2 = getFirstHtmlElement(page, xpathExpr);
        Assertions.assertEquals("List Users", userH2.getTextContent());
    }

    @Test
    void showAllUsers() throws Exception {
        Page page = webClient.getPage("http://localhost:8080/users/");
        String xpathExpr = "/html/body/div/h2";
        HtmlElement userH2 = getFirstHtmlElement(page, xpathExpr);
        Assertions.assertEquals("List Users", userH2.getTextContent());
    }

    @Test
    void createUser() throws IOException {
        String strUrl = "http://localhost:8080/users/";
        String body = "login=qweqwe&password=qweqwe&role=GUEST&createUser=";
        WebRequest requestSettings = prepareMethod(strUrl, body, HttpMethod.POST);

        Page page = webClient.getPage(requestSettings);
        String xpathExpr = "//*[@id=\"login\"]";
        HtmlElement loginField = getFirstHtmlElement(page, xpathExpr);
        Assertions.assertEquals("qweqwe", loginField.getTextContent());
    }

    private HtmlElement getFirstHtmlElement(Page page, String xpath) {
        HtmlPage htmlPage = (HtmlPage) page;
        List<Object> all = htmlPage.getByXPath(xpath);
        return (HtmlElement) all.get(0);
    }

    private WebRequest prepareMethod(String strUrl, String body, HttpMethod method) throws MalformedURLException {
        WebRequest requestSettings = new WebRequest(new URL(strUrl), method);
        requestSettings.setAdditionalHeader("Accept", "*/*");
        requestSettings.setAdditionalHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        requestSettings.setAdditionalHeader("Referer", "REFURLHERE");
        requestSettings.setAdditionalHeader("Accept-Language", "en-US,en;q=0.8");
        requestSettings.setAdditionalHeader("Accept-Encoding", "gzip,deflate,sdch");
        requestSettings.setAdditionalHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
        requestSettings.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
        requestSettings.setAdditionalHeader("Cache-Control", "no-cache");
        requestSettings.setAdditionalHeader("Pragma", "no-cache");
        requestSettings.setAdditionalHeader("Origin", strUrl);
        requestSettings.setRequestBody(body);
        return requestSettings;
    }
}