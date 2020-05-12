//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.charles.springcloud.tracing.sleuth.config;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallerRunsPolicy implements RejectedExecutionHandler {
    private static final Logger log = LoggerFactory.getLogger(CallerRunsPolicy.class);

    public CallerRunsPolicy() {
    }

    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException var4) {
                log.error("线程池阻塞任务时失败", var4);
            }
        }

    }
}
