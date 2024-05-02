package org.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.constants.SystemConstants;
import org.project.domain.ResponseResult;
import org.project.domain.entity.Link;
import org.project.domain.vo.LinkVo;
import org.project.mapper.LinkMapper;
import org.project.service.LinkService;
import org.project.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2024-04-16 20:23:13
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //检查审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(queryWrapper);
        //转换成VO
        List<LinkVo> linkVos;
        try {
            linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseResult.okResult(linkVos);
    }
}
