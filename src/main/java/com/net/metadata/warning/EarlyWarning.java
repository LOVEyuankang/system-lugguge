package com.net.metadata.warning;

import com.net.metadata.activemq.MessageSend;
import com.net.metadata.service.T_airport_warning_thresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 预警检查
 */
public class EarlyWarning {

    @Autowired
    private T_airport_warning_thresholdService t_airport_warning_thresholdService;
    @Autowired
    private MessageSend messageSend;

    /**
     * 出境航班行李交接时间临近警告, 每隔2s时间向队列发送消息
     */
    @Scheduled(fixedDelay=2000)
    public void outboundFlightBaggageHandoverTimeNearWarning(){

    }
    /**
     * 未分拣删除预警, 每隔2s时间向队列发送消息
     */
    @Scheduled(fixedDelay=2000)
    public void notSortingDeletionWarning(){

    }

    /**
     * 行李运输质量考核预警, 每隔2s时间向队列发送消息
     */
    @Scheduled(fixedDelay=2000)
    public void baggageTransportationQualityAssessmentEarlyWarning(){

    }

    /**
     * 实际行李扫描数据与离港系统不一致预警, 每隔2s时间向队列发送消息
     */
    @Scheduled(fixedDelay=2000)
    public void actualBaggageScanningDataIsInconsistentWithDepartureSystemEarlyWarning(){

    }

    /**
     * 航班保障时间修改预警, 每隔2s时间向队列发送消息
     */
    @Scheduled(fixedDelay=2000)
    public void flightGuaranteeTimeModificationWarning(){

    }



}
