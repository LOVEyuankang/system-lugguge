package com.net.metadata.service.impl;

import com.net.metadata.entity.Postinfo;
import com.net.metadata.mapper.PostinfoMapper;
import com.net.metadata.service.PostinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PostinfoServiceImpl implements PostinfoService {

    @SuppressWarnings("unused")
	private static Logger LOGGER = LoggerFactory.getLogger(PostinfoServiceImpl.class);

    @Autowired
    private PostinfoMapper postinfoMapper;



    @Override
    public void addPostinfo(Postinfo resource) {
        postinfoMapper.insert(resource);
    }

    @Override
    public void updatePostinfo(Postinfo resource) {
        int update = postinfoMapper.updatePostinfo(resource);
        if (update != 1) {
            throw new RuntimeException("更新失败");
        }
    }

    @Override
    public Postinfo findPostinfoById(Long id) {
        return postinfoMapper.findPostinfoById(id);
    }

    @Override
    public void deletePostinfoById(Long id) {
        int delete = postinfoMapper.deletePostinfoById(id);
        if (delete != 1) {
            throw new RuntimeException("删除失败");
        }
    }

    public static void main(String[] args) {
        Long abc = 126L;
        Long def = 126L;
        System.out.println(abc == def);
    }

	@Override
	public List<Postinfo> findPostinfoAll() {
		return null;
	}

	@Override
	public List<Postinfo> findPostinfoAllByDeptid(String deptid) {
		return postinfoMapper.findPostinfoAllByDeptid(deptid);
	}

}
