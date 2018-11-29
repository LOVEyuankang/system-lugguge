package com.net.metadata.websocket;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.net.metadata.service.T_airport_dyndepfltService;
import com.net.metadata.service.T_airport_luggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * WebSocket 消息推送
 */
@Controller
@RequestMapping(value = "/checkcenter")
public class CheckCenterController {

    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private T_airport_dyndepfltService t_airport_dyndepfltService;
    @Autowired
    private T_airport_luggageService t_airport_luggageService;

    //页面请求  @PathVariable
    @GetMapping("/view")
    public String view() {
        return "/socket";
    }

    @GetMapping("/index3")
    public String index3(){
        return "/index3";
    }

    @GetMapping("/index2")
    public String index2(){
        return "/index2";
    }

    @Scheduled(fixedDelay=5000)
    public void show(){
        Map<String, List> map = Maps.newHashMap();
        List<Integer> databz = Lists.newArrayList();
        List<Integer> dataybz = Lists.newArrayList();
        List<Integer> dataxlzs = Lists.newArrayList();
        List<Integer> datayzcxl = Lists.newArrayList();
        Calendar cal = Calendar.getInstance();
        int hour2 = cal.get(Calendar.HOUR_OF_DAY);//24小时制
        for (int i = 0; i <= 13; i++) {
            String ii = i < 10 ? "0" + String.valueOf(i) : String.valueOf(i);
            databz.add(t_airport_dyndepfltService.findT_airport_dyndepfltAllbytime(ii));
            dataybz.add(t_airport_dyndepfltService.findT_airport_dyndepfltAllbyStatusbytime(ii));
            dataxlzs.add(t_airport_luggageService .findT_airport_luggageAllbytime(ii));
            datayzcxl.add(t_airport_luggageService .findT_airport_luggageAllbystutastime(ii));
        }
        map.put("databz",databz);
        map.put("dataybz",dataybz);
        map.put("dataxlzs",dataxlzs);
        map.put("datayzcxl",datayzcxl);

        map.keySet().forEach(s -> webSocketService.setMessage(s +":"+map.get(s)));
    }

}
