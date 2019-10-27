//package com.money.transfer.common;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import net.minidev.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@Component
//public class NodesInfo {
//    //node1 -> constellationPub
//    HashMap<String, String> allNodesInfo;
//
//    public NodesInfo(@Value("${companiesInfo}") String nodesInfo) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        HashMap<String, HashMap<String, String>> companiesInformation = (HashMap<String, HashMap<String, String>>) mapper.readValue(nodesInfo, JSONObject.class).get("companies");
//
//        List<HashMap<String, String>> nodesInformation = (ArrayList<HashMap<String, String>>) mapper.readValue(nodesInfo, JSONObject.class).get("nodes");
//        nodesInformation.forEach(nodeInfo -> {
////                final String name;
////                final String constelationPub;
////                nodeInfo.forEach((key, val) -> {
////                    if (key.equals("name")) {
////                        name = (String) val;
////                    }
////                    if (key.equals("constellationPub")) {
////                        constelationPub = val;
////                    }
////                });
////            }
////            allNodesInfo.put(name, constelationPub);
//        System.out.println(allNodesInfo);
//    });
//}
//}
