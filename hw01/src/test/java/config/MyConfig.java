package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:test.properties")
public interface MyConfig extends Config {
    @Key("test.url")
    String url();

    @DefaultValue("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям")
    String title();
}
