package com.net.metadata.service.impl;

import com.google.common.collect.Lists;
import com.net.metadata.entity.Place;
import com.net.metadata.mapper.PlaceMapper;
import com.net.metadata.service.PlaceService;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceMapper placeMapper;
	
	@Override
	public List<Tree> findTree() {
		   List<Tree> trees = Lists.newArrayList();
	        List<Place> places = placeMapper.findPlaceAll();
	        for (Place place : places) {
	            Tree tree = new Tree();
	            tree.setId(place.getPlace_no());
	            tree.setText(place.getCSNAME());
	            trees.add(tree);
	        }
	        return trees;
	}

	@Override
	public void findPlaceDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(placeMapper.findPlacePageCondition(pageInfo));
		pageInfo.setTotal(placeMapper.findPlacePageCount(pageInfo));
	}

	@Override
	public void addPlace(Place place) {
		placeMapper.insert(place);
	}

	@Override
	public void updatePlace(Place place) {
		placeMapper.updatePlace(place);
	}

	@Override
	public Place findPlaceById(Long place_no) {
		return placeMapper.findPlaceById(place_no);
	}

	@Override
	public void deletePlaceById(Long place_no) {
		placeMapper.deletePlaceById(place_no);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findPlaceAll() {
		return placeMapper.findPlaceAll();
	}

	@Override
	public Place findPlaceByplace_no(Long place_no) {
		return placeMapper.findPlaceByplace_no(place_no);
	}

}
