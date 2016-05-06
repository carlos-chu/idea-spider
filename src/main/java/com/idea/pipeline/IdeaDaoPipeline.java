package com.idea.pipeline;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.idea.dao.IdeaDAO;
import com.idea.model.IdeaInfo;

@Component
public class IdeaDaoPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IdeaDAO ideaDAO;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<IdeaInfo> ideas = resultItems.get("ideas");
        for (IdeaInfo i : ideas) {
            logger.info("爬取的idea为：{}", i);
        }
    }
}
