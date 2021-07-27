package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.dto.response.TagDTO;
import com.example.MyBookShopApp.entity.book.links.Book2AuthorEntity;
import com.example.MyBookShopApp.entity.tag.TagEntity;
import com.example.MyBookShopApp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());


    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagEntity getOneTag(Integer id) {
        return tagRepository.findById(id).get();
    }

    public List<TagDTO> getTagsIndexPage() {
        List<Object[]> objectsTags = tagRepository.tagsForIndexPage();
        List<TagDTO> tagDTOList = new ArrayList<>();

        Map<Integer, String> mapClass = getMapClass(objectsTags);

        for (Object[] obj : objectsTags) {
            TagDTO tagDTO = new TagDTO();
            tagDTO.setId((int) obj[0]);
            tagDTO.setTag((String) obj[1]);
            tagDTO.setClassTag(mapClass.get(new BigInteger(obj[2].toString()).intValue()));
            tagDTOList.add(tagDTO);
        }

        Comparator<TagDTO> comparator = (p1, p2) -> p1.getId() - p2.getId();
        tagDTOList.sort(comparator);
        return tagDTOList;
    }

    private Map<Integer, String> getMapClass(List<Object[]> objects) {
        List<Integer> list = objects.stream().map(o -> new BigInteger(o[2].toString()).intValue()).distinct().sorted().collect(Collectors.toList());
        ArrayList<String> newClass = new ArrayList<>(Arrays.asList("Tag_xs", "Tag_sm", "Tag_md", "Tag_lg"));
        Map<Integer, String> mapClass = new HashMap<>();

        if (list.size() < 5) {
            for (int i = 0; i < list.size(); i++) {
                mapClass.put(list.get(i), newClass.get(i));
            }
        } else {
            mapClass.put(list.get(0), newClass.get(0));
            mapClass.put(list.get(1), newClass.get(1));
            mapClass.put(list.get(list.size() - 2), newClass.get(2));
            mapClass.put(list.get(list.size() - 1), newClass.get(3));
        }
        return mapClass;
    }

}
