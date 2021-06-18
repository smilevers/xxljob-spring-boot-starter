package cn.smilevers.xxljob.spring.boot.autoconfigure;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ConditionalOnProperty(prefix = "xxl.job", name = {"admin", "executor"}, matchIfMissing = true)
public class XxlJobConfig {

    public static Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    private XxljobProperties xxljobProperties;

    public XxlJobConfig(XxljobProperties xxljobProperties) {
        this.xxljobProperties = xxljobProperties;
    }

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxljobProperties.getAdmin().getAddresses());
        xxlJobSpringExecutor.setAppname(xxljobProperties.getExecutor().getAppName());
        xxlJobSpringExecutor.setAddress(xxljobProperties.getExecutor().getAddress());
        xxlJobSpringExecutor.setIp(xxljobProperties.getExecutor().getIp());
        xxlJobSpringExecutor.setPort(Integer.valueOf(xxljobProperties.getExecutor().getPort()));
        xxlJobSpringExecutor.setAccessToken(xxljobProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxljobProperties.getExecutor().getLogPath());
        xxlJobSpringExecutor
                .setLogRetentionDays(xxljobProperties.getExecutor().getLogretentiondays());
        return xxlJobSpringExecutor;
    }

    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     *
     * 1、引入依赖： <dependency> <groupId>org.springframework.cloud</groupId>
     * <artifactId>spring-cloud-commons</artifactId> <version>${version}</version>
     * </dependency>
     *
     * 2、配置文件，或者容器启动变量 spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     *
     * 3、获取IP String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */
}
