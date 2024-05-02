package org.project.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    private Long id;

    private Long categoryId;

    private String categoryName;

    private String content;

    private Date createTime;

    private String isComment;

    private String title;

    private Long viewCount;

}
