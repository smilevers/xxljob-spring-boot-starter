package cn.smilevers.xxljob.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "xxl.job")
public class XxljobProperties {
    private XxljobAdminProperties admin;
    private XxljobExecutorProperties executor;
    private String accessToken;

    public XxljobAdminProperties getAdmin() {
        return admin;
    }

    public void setAdmin(XxljobAdminProperties admin) {
        this.admin = admin;
    }

    public XxljobExecutorProperties getExecutor() {
        return executor;
    }

    public void setExecutor(XxljobExecutorProperties executor) {
        this.executor = executor;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
