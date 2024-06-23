package org.project.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 文章表(Article)表实体类
 *
 * @author makejava
 * @since 2024-04-13 10:26:34
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sg_article")
public class Article {

    @TableId(value = "id")
    private Long id;
//标题
    private String title;
//文章内容
    private String content;
//文章摘要
    private String summary;
//所属分类id
    private Long categoryId;

    @TableField(exist = false)
    private String categoryName;
//缩略图
    private String thumbnail;
//是否置顶（0否，1是）
    private String isTop;
//状态（0已发布，1草稿）
    private String status;
//访问量
    private Long viewCount;
//是否允许评论 1是，0否
    private String isComment;

    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
//删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

    //用于更新浏览量
    public Article(Long id, long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}

