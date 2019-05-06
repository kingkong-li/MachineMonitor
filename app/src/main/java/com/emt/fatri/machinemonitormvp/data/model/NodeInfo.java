package com.emt.fatri.machinemonitormvp.data.model;

/**
 * description:节点信息类，包括监测节点信息封装和数据的解析。
 * Created by kingkong on 2018/5/10 0010.
 * changed by kingkong on 2018/5/10 0010.
 */

public class NodeInfo {
    /**节点id 字段*/
    public static final String NODE_ID = "nodeId";
    /**节点描述 字段*/
    public static final String DESCRIPTION = "description";
    /**节点状态码 字段*/
    public static final String STATE_CODE = "stateCode";
    /**节点id */
    public int nodeId;
    /**节点描述*/
    public String description;
    /**节点状态码*/
    public int stateCode;

    @Override
    public String toString() {
        return "NodeInfo ---"
                +NODE_ID+":"+nodeId
                +DESCRIPTION+":"+description
                +STATE_CODE+":"+stateCode;
    }
}
