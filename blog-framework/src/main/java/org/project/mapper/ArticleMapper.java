package org.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.project.domain.entity.Article;
import org.project.domain.entity.Tag;

import java.util.List;


/**
 * 文章表(Article)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-17 22:10:42
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<String> selectTagByArticleId(Long id);
}

