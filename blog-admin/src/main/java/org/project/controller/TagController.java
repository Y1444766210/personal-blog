package org.project.controller;

import org.project.domain.ResponseResult;
import org.project.domain.dto.TagDTO;
import org.project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult getTagList(Integer pageNum, Integer pageSize, TagDTO tagDTO) {
        return tagService.getTagList(pageNum, pageSize, tagDTO);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagDTO tagDTO) {
        return tagService.addTag(tagDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseResult removeTag(@PathVariable String id) {
        return tagService.removeTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTag(@PathVariable String id) {
        return tagService.getTag(id);
    }

    @PutMapping
    public ResponseResult editTag(@RequestBody TagDTO tagDTO) {
        return tagService.editTag(tagDTO);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {
        return tagService.listAllTag();
    }


}
