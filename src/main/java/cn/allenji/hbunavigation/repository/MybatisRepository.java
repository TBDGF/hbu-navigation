package cn.allenji.hbunavigation.repository;

import cn.allenji.hbunavigation.domain.port.GraphRepository;

public class MybatisRepository implements GraphRepository {
    @Override
    public void updateGraph() {

    }

    @Override
    public void saveGraph() {

    }

    @Override
    public void getGraph(){
        System.out.println("get graph");
    }
}
