package com.example.recycle1.data.dto.mapper;

import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.dto.CourseDTO;
import com.example.recycle1.data.model.Association;
import com.example.recycle1.data.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {

    public static List<Course> map (List<CourseDTO> courseDTOList) {
        List<Course> courseList = new ArrayList<>();
        for(CourseDTO courseDTO : courseDTOList) {
            courseList.add(map(courseDTO));
        }
        return courseList;
    }

    public static Course map(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCreatedAt(courseDTO.getCreatedAt());
        course.setEndOn(courseDTO.getEndOn());
        course.setLocation(courseDTO.getLocation());
        course.setStartOn(courseDTO.getStartOn());
        course.setTheme(courseDTO.getTheme());
        course.setId(courseDTO.getId());
        return course;
    }
}
