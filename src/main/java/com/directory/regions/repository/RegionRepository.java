package com.directory.regions.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.directory.regions.model.Region;
import com.directory.regions.typeHandler.UuidTypeHandler;

@Mapper
public interface RegionRepository {
    @Select("SELECT id, name, short_name FROM regions LIMIT #{limit} OFFSET #{offset}")
    @Results({
        @Result(column = "id", property = "id", typeHandler = UuidTypeHandler.class),
        @Result(column = "name", property = "name"),
        @Result(column = "short_name", property = "shortName")
    })
    List<Region> findAll(@Param("limit") int limit, @Param("offset") int offset);

    @Insert("INSERT INTO regions (id, name, short_name) VALUES (#{id, typeHandler=com.directory.regions.typeHandler.UuidTypeHandler}, #{name}, #{shortName})")
    void insert(Region region);

    @Delete("DELETE FROM regions WHERE id = #{id, typeHandler=com.directory.regions.typeHandler.UuidTypeHandler}")
    void deleteById(Region region);

    @Update("UPDATE regions SET name = #{name}, short_name = #{shortName} WHERE id = #{id, typeHandler=com.directory.regions.typeHandler.UuidTypeHandler}")
    void update(Region region);

    @Select("SELECT id, name, short_name FROM regions WHERE id = #{id, typeHandler=com.directory.regions.typeHandler.UuidTypeHandler}")
    @Results({
        @Result(column = "id", property = "id", typeHandler = UuidTypeHandler.class),
        @Result(column = "name", property = "name"),
        @Result(column = "short_name", property = "shortName")
    })
    Region findByIdRegion(Region region);

    @Select("SELECT id, name, short_name FROM regions WHERE short_name = #{shortName} LIMIT #{limit} OFFSET #{offset}")
    @Results({
        @Result(column = "id", property = "id", typeHandler = UuidTypeHandler.class),
        @Result(column = "name", property = "name"),
        @Result(column = "short_name", property = "shortName")
    })
    List<Region> findByShortNameRegion(@Param("shortName") String shortName, @Param("limit") int limit, @Param("offset") int offset);

}
