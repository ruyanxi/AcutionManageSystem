package com.zte.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 网站基础信息配置类
 * @author Administrator
 *
 */
@Component
@PropertySource(value="classpath:site.properties")
public class SiteConfig {
	
	@Value("${zte.site.name}")
	private String siteName;
	@Value("${zte.site.url}")
	private String siteUrl;
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	
	
}
