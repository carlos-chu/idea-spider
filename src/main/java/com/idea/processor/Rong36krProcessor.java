package com.idea.processor;

import java.util.ArrayList;
import java.util.List;

import javax.management.JMException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import com.alibaba.fastjson.JSON;
import com.idea.model.IdeaInfo;
import com.idea.model.Rong36krInfo;
import com.idea.pipeline.IdeaDaoPipeline;

@Component
public class Rong36krProcessor implements PageProcessor {

    private static final String LIST_URL = "https://rong.36kr.com/api/company.*";
    private static final String START_URL = "https://rong.36kr.com/api/company?fincestatus=1&type=2&page=1";
    private final static String COOKIE_DOMAIN = ".36kr.com";
    private Site site = Site.me().addCookie(COOKIE_DOMAIN, "krid_user_id", "28024")
            .addCookie(COOKIE_DOMAIN, "_kr_p_se", "5bd5a776-1d90-4574-950b-e583b8a5de2c")
            .addCookie(COOKIE_DOMAIN, "kr_plus_id", "62503")
            .addCookie(COOKIE_DOMAIN, "kr_plus_token", "03576a22a6ee966691a6bdec0e40cedc678d3177")
            .addCookie(COOKIE_DOMAIN, "krid_user_version", "1004").addCookie("kwlo_iv", "1");

    @Autowired
    private IdeaDaoPipeline pagePipeline;

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(LIST_URL).match()) {
            String pages = new JsonPathSelector("$.data.page.totalPages").select(page.getRawText());
            // 列表页 第一页进行后续爬取
            if (page.getUrl().toString().contains("page=1")) {
                for (int i = 2; i <= Integer.valueOf(pages); i++) {
                    page.addTargetRequest("https://rong.36kr.com/api/company?fincestatus=1&type=2&page=" + i);
                }
            }
        }
        List<String> ideasString = new JsonPathSelector("$.data.page.data").selectList(page.getRawText());
        List<Rong36krInfo> infos = JSON.parseArray(ideasString.toString(), Rong36krInfo.class);
        List<IdeaInfo> ideas = new ArrayList<IdeaInfo>();
        for (Rong36krInfo r : infos) {
            ideas.add(r.getCompany());
        }
        page.putField("ideas", ideas);
    }

    @Override
    public Site getSite() {
        return site;
    }

    private void invoke(Rong36krProcessor processor) {
        try {
            ProcessHandler.instance().addQueue(processor).crawl(START_URL, pagePipeline);
        } catch (JMException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/spring/applicationContext*.xml");
        Rong36krProcessor processor = applicationContext.getBean(Rong36krProcessor.class);
        processor.invoke(processor);
    }
}
