package cn.allenji.hbunavigation.usecase.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class WebPath {
    List<String> edges;
    List<String> nodes;

    public WebPath() {
        edges=new LinkedList<>();
        nodes=new LinkedList<>();
    }
}
