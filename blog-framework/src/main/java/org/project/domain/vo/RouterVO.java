package org.project.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.domain.entity.Menu;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouterVO {

    private List<Menu> menus;
}
