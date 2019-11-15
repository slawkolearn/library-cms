package pl.edu.pjatk.librarycms.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, pl.edu.pjatk.librarycms.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, pl.edu.pjatk.librarycms.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, pl.edu.pjatk.librarycms.domain.User.class.getName());
            createCache(cm, pl.edu.pjatk.librarycms.domain.Authority.class.getName());
            createCache(cm, pl.edu.pjatk.librarycms.domain.User.class.getName() + ".authorities");
            createCache(cm, pl.edu.pjatk.librarycms.domain.Worker.class.getName());
            createCache(cm, pl.edu.pjatk.librarycms.domain.Client.class.getName());
            createCache(cm, pl.edu.pjatk.librarycms.domain.Client.class.getName() + ".loans");
            createCache(cm, pl.edu.pjatk.librarycms.domain.Book.class.getName());
            createCache(cm, pl.edu.pjatk.librarycms.domain.Book.class.getName() + ".ratings");
            createCache(cm, pl.edu.pjatk.librarycms.domain.Book.class.getName() + ".loans");
            createCache(cm, pl.edu.pjatk.librarycms.domain.Book.class.getName() + ".overdueFeeRates");
            createCache(cm, pl.edu.pjatk.librarycms.domain.Author.class.getName());
            createCache(cm, pl.edu.pjatk.librarycms.domain.Author.class.getName() + ".books");
            createCache(cm, pl.edu.pjatk.librarycms.domain.Rating.class.getName());
            createCache(cm, pl.edu.pjatk.librarycms.domain.BookLoan.class.getName());
            createCache(cm, pl.edu.pjatk.librarycms.domain.BookLoan.class.getName() + ".fees");
            createCache(cm, pl.edu.pjatk.librarycms.domain.OverdueFeeRate.class.getName());
            createCache(cm, pl.edu.pjatk.librarycms.domain.OverdueFeeRate.class.getName() + ".books");
            createCache(cm, pl.edu.pjatk.librarycms.domain.Fee.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
