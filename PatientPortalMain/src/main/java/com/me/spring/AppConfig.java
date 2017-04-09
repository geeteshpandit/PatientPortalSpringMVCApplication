package com.me.spring;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.me.dao.ClinicsDAO;





@Configuration
@EnableCaching
@ComponentScan({ "com.me.spring.*" })
public class AppConfig {

		@Bean
		public CacheManager cacheManager() {
			EhCacheCacheManager cacheManager = new EhCacheCacheManager();
	        cacheManager.setCacheManager(ehCacheCacheManager().getObject());
	        return cacheManager;
		}

		@Bean
		public EhCacheManagerFactoryBean ehCacheCacheManager() {
			EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
			cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
			cmfb.setShared(true);
			return cmfb;
		}
		
		@Bean 
		   public ClinicsDAO clinicDAO(){
		      return new ClinicsDAO();
		   }
		

}
