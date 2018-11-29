package com.net.metadata.service;


import com.net.metadata.entity.Postinfo;

import java.util.List;

/**
 * @description：岗位管理
 * @author：ccj
 * @date：2015/10/1 14:51
 */
public interface PostinfoService {
    /**
     * 查询所有岗位
     *
     * @return
     */
    List<Postinfo> findPostinfoAll();

    /**
     * 添加岗位
     *
     * @param postinfo
     */
    void addPostinfo(Postinfo postinfo);


    /**
     * 更新岗位
     *
     * @param postinfo
     */
    void updatePostinfo(Postinfo postinfo);

    /**
     * 根据id查询岗位
     *
     * @param id
     * @return
     */
    Postinfo findPostinfoById(Long id);
    
    /**
     * 根据id查询岗位
     *
     * @param id
     * @return
     */
    List<Postinfo> findPostinfoAllByDeptid(String deptid);

    /**
     * 根据id删除岗位
     *
     * @param id
     */
    void deletePostinfoById(Long id);

}
