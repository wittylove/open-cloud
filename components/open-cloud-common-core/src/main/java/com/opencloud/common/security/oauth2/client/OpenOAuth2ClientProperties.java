package com.opencloud.common.security.oauth2.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author: liuyadu
 * @date: 2019/2/14 14:34
 * @description:
 */
@ConfigurationProperties(prefix = "opencloud.client")
public class OpenOAuth2ClientProperties {

    private Map<String, OpenOAuth2ClientDetails> oauth2;

    public Map<String, OpenOAuth2ClientDetails> getOauth2() {
        return oauth2;
    }

    public void setOauth2(Map<String, OpenOAuth2ClientDetails> oauth2) {
        this.oauth2 = oauth2;
    }
}
