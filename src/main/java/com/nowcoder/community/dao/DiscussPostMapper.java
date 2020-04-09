package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//相当于@Repositity
@Mapper
public interface DiscussPostMapper {
    
    // 2020/4/9重构，添加参数orderMode，0默认原来的来，1按照热度排序
    List<DiscussPost> selectDiscussPosts(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit, @Param("orderMode") int orderMode);
    
    // @Parm给参数起别名
    // 如果参数只有一个参数，并且在<if>中使用，必须加别名.
    int selectDiscussPostRows(@Param("userId") int userId);
    
    int insertDiscussPost(DiscussPost discussPost);
    
    DiscussPost selectDiscussPostById(int id);
    
    int updateCommentCount(int id, int commentCount);
    
    int updateType(int id, int type);
    
    int updateStatus(int id, int status);
    
    int updateScore(int id, double score);
}
