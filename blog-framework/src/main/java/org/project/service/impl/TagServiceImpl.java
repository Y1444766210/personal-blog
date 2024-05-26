package org.project.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.constants.SystemConstants;
import org.project.domain.ResponseResult;
import org.project.domain.dto.TagDTO;
import org.project.domain.entity.Tag;
import org.project.domain.vo.PageVo;
import org.project.domain.vo.TagVO;
import org.project.mapper.TagMapper;
import org.project.service.TagService;
import org.project.utils.BeanCopyUtils;
import org.project.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2024-05-03 18:43:31
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult getTagList(Integer pageNum, Integer pageSize, TagDTO tagDTO) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagDTO.getName()), Tag::getName, tagDTO.getName());
        queryWrapper.eq(StringUtils.hasText(tagDTO.getRemark()), Tag::getRemark, tagDTO.getRemark());
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        List<Tag> tagList = page.getRecords();
        List<TagVO> tags = tagList.stream()
                .map(tag -> BeanCopyUtils.copyBean(tag, TagVO.class))
                .collect(Collectors.toList());
        //封装数据返回
        PageVo pageVo = new PageVo(tags, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult removeTag(String id) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getId, id);
        remove(queryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editTag(String id, TagDTO tagDTO) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getId, id);
        Tag tag = BeanCopyUtils.copyBean(tagDTO, Tag.class);
        tag.setUpdateTime(DateTime.now());
        update(tag, queryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addTag(TagDTO tagDTO) {
        Tag tag = BeanCopyUtils.copyBean(tagDTO, Tag.class);
        tag.setCreateTime(DateTime.now());
        save(tag);
        return ResponseResult.okResult();
    }
}
