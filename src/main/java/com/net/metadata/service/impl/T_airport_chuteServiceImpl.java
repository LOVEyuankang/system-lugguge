package com.net.metadata.service.impl;

import com.google.common.collect.Lists;
import com.net.metadata.entity.T_airport_chute;
import com.net.metadata.mapper.T_airport_chuteMapper;
import com.net.metadata.service.T_airport_chuteService;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class T_airport_chuteServiceImpl implements T_airport_chuteService {

	@Autowired
	private T_airport_chuteMapper t_airport_chuteMapper;
	
	@Override
	public void addT_airport_chute(T_airport_chute t_airport_chute) {
		t_airport_chuteMapper.insert(t_airport_chute);
	}

	@Override
	public void updateT_airport_chute(T_airport_chute t_airport_chute) {
		t_airport_chuteMapper.updateT_airport_chute(t_airport_chute);
	}

	@Override
	public T_airport_chute findT_airport_chuteById(Long ID) {
		return t_airport_chuteMapper.findT_airport_chuteById(ID);
	}

	@Override
	public void deleteT_airport_chuteById(Long ID) {
		t_airport_chuteMapper.deleteT_airport_chuteById(ID);
	}

	@Override
	public void findT_airport_chuteDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(t_airport_chuteMapper.findT_airport_chutePageCondition(pageInfo));
		pageInfo.setTotal(t_airport_chuteMapper.findT_airport_chutePageCount(pageInfo));
	}

	@Override
	public List<Tree> findTree() {
		   List<Tree> trees = Lists.newArrayList();
	        List<T_airport_chute> t_airport_chutes = t_airport_chuteMapper.findT_airport_chuteAll();
	        for (T_airport_chute t_airport_chute : t_airport_chutes) {
	            Tree tree = new Tree();
	            tree.setPid(t_airport_chute.getCODE());
	            tree.setText(t_airport_chute.getTYPE());
	            trees.add(tree);
	        }
	        return trees;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List findT_airport_chuteAll() {
		return t_airport_chuteMapper.findT_airport_chuteAll();
	}


	@Override
	public T_airport_chute findT_airport_chuteByCODE(String CODE) {
		return t_airport_chuteMapper.findT_airport_chuteByCODE(CODE);
	}


}
