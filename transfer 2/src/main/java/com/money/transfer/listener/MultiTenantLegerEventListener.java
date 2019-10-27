package com.money.transfer.listener;

import com.money.transfer.common.TenantContext;
import com.money.transfer.topic.handlers.AddEventTopicHandler;
import com.money.transfer.topic.handlers.UpdateEventTopicHandler;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Async;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class MultiTenantLegerEventListener {
    public TenantContext tenantContext;
    private Map<String, String> handlerMap = new HashMap<>();


    public MultiTenantLegerEventListener(TenantContext tenantContext) {
        this.tenantContext = tenantContext;
        handlerMap.put("0x616ea41f1d25108990ce3315d377a615ededd0a83e4cdd7fa4daafa31a71724b", "updateEvent");
        handlerMap.put("0xb81041a32a7a5e778a41739306638fbba81e6b766d123a5664a3a6bf18959912", "addEvent");
        initializeListeners();
    }

    private void initializeListeners() {
        tenantContext.getNodeRequestConfigs().values().forEach(nodeRequestConfig -> {
            ScheduledExecutorService scheduledExecutorService = Async.defaultExecutorService();

            Admin admin = Admin.build(getHttpService(nodeRequestConfig.getQuorumIp() + ":" + nodeRequestConfig.getQuorumPort()), 100, scheduledExecutorService);

            EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST, Collections.emptyList());

            admin.ethLogFlowable(filter).subscribe(log -> {
                tenantContext.setCurrentTenant(nodeRequestConfig.getName());
                System.out.println(tenantContext.currentTenant);
                handleTopic(log);
            });
        });
    }

    private void handleTopic(Log log) {
        System.out.println("This is a log of type " + handlerMap.get(log.getTopics().get(0)));
        if (handlerMap.get(log.getTopics().get(0)).equalsIgnoreCase("addEvent")) {
            AddEventTopicHandler.handle(log);
        } else {
            UpdateEventTopicHandler.handle(log);
        }
    }

    public HttpService getHttpService(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(100000, TimeUnit.MILLISECONDS)
            .readTimeout(100000, TimeUnit.MILLISECONDS)
            .connectionSpecs(List.of(ConnectionSpec.CLEARTEXT))
            .build();
        return new HttpService(url, okHttpClient, false);
    }


}
