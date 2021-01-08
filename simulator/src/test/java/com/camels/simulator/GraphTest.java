package com.camels.simulator;

import org.junit.jupiter.api.Test;

public class GraphTest {
    @Test
    public void mapTest(){
        Map testmap = new Map();
        System.out.println(testmap.getMap());
    }

    @Test
    public void vertexTest(){
        Map testmap = new Map();
        System.out.println(testmap.getVertices());
    }

    @Test
    public void edgeTest(){
        Map testmap = new Map();
        System.out.println(testmap.getEdges());
    }
}
