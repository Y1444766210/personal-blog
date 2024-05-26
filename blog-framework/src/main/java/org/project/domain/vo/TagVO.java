package org.project.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagVO {
    //标签id
    private Long id;
    //标签名
    private String name;
    //备注
    private String remark;
}
