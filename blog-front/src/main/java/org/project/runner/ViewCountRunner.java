package org.project.runner;

import io.lettuce.core.dynamic.annotation.CommandNaming;
import org.project.domain.entity.Article;
import org.project.mapper.ArticleMapper;
import org.project.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询文章列表，将id和viewcount封装到map集合
        List<Article> articles = articleMapper.selectList(null);
        //value值选用Integer，因为后面需要用到increment方法实现浏览量的自增，而Long类型写入redis实际上是一个1L的形式，无法自增
        Map<String, Integer> map = new HashMap<>();
        for (Article article : articles) {
            map.put(article.getId().toString(), article.getViewCount().intValue());
        }
        //存入redis
        redisCache.setCacheMap("article:viewCount", map);
    }
}
