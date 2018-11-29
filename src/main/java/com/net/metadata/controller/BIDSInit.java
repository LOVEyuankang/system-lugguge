package com.net.metadata.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.net.metadata.entity.Chute;
import com.net.metadata.entity.InitFltBags;
import com.net.metadata.service.ChuteService;
import com.net.metadata.service.InitFltBagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bids")
public class BIDSInit {

	@Autowired
	private InitFltBagsService initFltBagsService;
	
	@Autowired
	private ChuteService chuteService;
	
	@RequestMapping("/init")
	@ResponseBody
	public List<InitFltBags> init(String ip) throws JsonProcessingException {
		List<InitFltBags> list=initFltBagsService.findInit(ip);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return list;
	}
	
	@RequestMapping("/getAllChute")
	@ResponseBody
	public List<Chute> getAllChute(){
		return chuteService.findAll();
	}
}
