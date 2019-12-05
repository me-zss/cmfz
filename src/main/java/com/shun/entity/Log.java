package com.shun.entity;

import io.netty.channel.ChannelHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Log implements Serializable {
    private String name;
    private String datetime;
    private String action;
    private String status;
    private Object[] args;

    public String argsToString() {
        StringBuilder sb = new StringBuilder();
        if (args!=null&&args.length>=0) {
            for (int i=1;i<=args.length;i++) {
                Object arg = args[i-1];
                sb.append("参数"+i+"：{");
                if(arg instanceof String[]){
                    sb.append("String[]{");
                    for (String s : ((String[]) arg)) {
                        sb.append(s+",");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    sb.append("}");
                }else{
                    sb.append(arg.toString());
                }
                sb.append("}");
            }
        }
        return sb.toString();
    }
}
