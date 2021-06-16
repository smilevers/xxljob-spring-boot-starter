package cn.smilevers.xxljob.spring.boot.autoconfigure;

import com.xxl.job.core.executor.XxlJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: xxljob configuration
 * </p>
 *
 * @author baixufeng
 * @version 2021/4/20 10:19:36
 */
@Configuration
@EnableConfigurationProperties(XxljobProperties.class)
@ConditionalOnProperty(prefix = "xxl.job")
public class XxlJobConfig {

    public static Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Autowired
    private XxljobProperties xxljobProperties;


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
        logger.info("====xxl-job config init====");
        XxlJobExecutor xxlJobSpringExecutor = new XxlJobExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxljobProperties.getAdmin().getAddresses());
        xxlJobSpringExecutor.setAppName(xxljobProperties.getExecutor().getAppName());
        xxlJobSpringExecutor.setIp(xxljobProperties.getExecutor().getIp());
        xxlJobSpringExecutor.setPort(Integer.valueOf(xxljobProperties.getExecutor().getPort()));
        xxlJobSpringExecutor.setAccessToken(xxljobProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxljobProperties.getExecutor().getLogPath());
        return xxlJobSpringExecutor;
    }
}
