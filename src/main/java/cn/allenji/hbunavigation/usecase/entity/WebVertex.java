package cn.allenji.hbunavigation.usecase.entity;

import cn.allenji.hbunavigation.domain.entity.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class WebVertex {
    private String id;
    private String information;
    private Map<String,Map<String,String>> style;

    public WebVertex(String label, String information) {
        this.id = label;
        this.information = information;
        this.style=new HashMap<>();
        Map<String,String> subLabel=new HashMap<>();
        subLabel.put("value",label);
        this.style.put("label",subLabel);
    }

    public WebVertex(Vertex vertex) {
        this.id = vertex.getLabel();
        this.information = vertex.getInformation();
        this.style=new HashMap<>();
        Map<String,String> subLabel=new HashMap<>();
        subLabel.put("value", vertex.getLabel());
        this.style.put("label",subLabel);
    }
}
