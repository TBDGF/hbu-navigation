package cn.allenji.hbunavigation.usecase.entity;


import cn.allenji.hbunavigation.domain.entity.Edge;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class WebEdge {
    String id;
    String source;
    String target;
    int weight;
    private Map<String,Map<String,String>> style;

    public WebEdge(String source, String target, String id, int weight) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.style=new HashMap<>();
        Map<String,String> subLabel=new HashMap<>();
        subLabel.put("value",weight+"m");
        this.style.put("label",subLabel);
    }


}
