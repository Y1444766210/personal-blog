package org.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.project.domain.ResponseResult;
import org.project.domain.dto.TagDTO;
import org.project.domain.entity.Tag;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2024-05-03 18:43:31
 */
public interface TagService extends IService<Tag> {

    ResponseResult addTag(TagDTO tagDTO);

    ResponseResult getTagList(Integer pageNum, Integer pageSize, TagDTO tagDTO);

    ResponseResult removeTag(String id);

    ResponseResult editTag(String id, TagDTO tagDTO);
}

