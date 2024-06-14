package com.oceanopedia.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OceanopediaResult {
    // status code, 200(success) or 500(failed)
    private int status;
    // result result
    private Object results;
    private String msg;
    // return data
    private Object data;
    // pagination adopted, this is to check whether there's more that current page is not showing
    private boolean hasMore;
    // clock down for the reservation
    private long time;
    private int cnt;


    public static OceanopediaResult ok(){
        OceanopediaResult OceanopediaResult = new OceanopediaResult();
        OceanopediaResult.setStatus(200);
        return OceanopediaResult;
    }

    public static OceanopediaResult ok(Object data){
        OceanopediaResult result = new OceanopediaResult();
        result.setStatus(200);
        result.setData(data);
        return result;
    }

    public static OceanopediaResult error(){
        OceanopediaResult result = new OceanopediaResult();
        result.setStatus(500);
        return result;
    }

    public static OceanopediaResult error(String msg){
        OceanopediaResult result = new OceanopediaResult();
        result.setStatus(500);
        result.setMsg(msg);
        return result;
    }
}
