package com.idea.processor;

import java.util.concurrent.LinkedBlockingQueue;

import javax.management.JMException;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatus;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @ClassName: SingletonProcessor
 * @Description: 爬取程序类
 * @author carlos.chu
 * @date 2016年5月4日 下午5:58:49
 */
public class ProcessHandler {

    private static ProcessHandler handler = new ProcessHandler();
    private int coreThead = Runtime.getRuntime().availableProcessors() * 2;
    private static LinkedBlockingQueue<PageProcessor> taskQueue = new LinkedBlockingQueue<PageProcessor>(1024);

    private ProcessHandler() {
    }

    public synchronized void crawl(String startUrl, Pipeline pipeline) throws JMException {
        while (!taskQueue.isEmpty()) {
            final PageProcessor processor = taskQueue.poll();
            Spider spider = Spider.create(processor).addPipeline(pipeline).addUrl(startUrl).thread(coreThead);
            SpiderMonitor spiderMonitor = new SpiderMonitor() {
                @Override
                protected SpiderStatusMXBean getSpiderStatusMBean(Spider spider,
                        MonitorSpiderListener monitorSpiderListener) {
                    SpiderStatus status = new SpiderStatus(spider, monitorSpiderListener);
                    if (status.getLeftPageCount() != -1) {
                        spider.run();
                    } else {
                        addQueue(processor);
                    }
                    return status;
                }
            };
            spiderMonitor.register(spider);
        }
    }

    public ProcessHandler addQueue(PageProcessor processor) {
        taskQueue.add(processor);
        return this;
    }

    public static ProcessHandler instance() {
        return handler;
    }
}
