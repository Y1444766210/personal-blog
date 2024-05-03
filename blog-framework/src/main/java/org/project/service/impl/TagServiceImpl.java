package org.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.domain.entity.Tag;
import org.project.mapper.TagMapper;
import org.project.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2024-05-03 18:43:31
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
