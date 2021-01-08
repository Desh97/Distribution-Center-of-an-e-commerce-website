package com.camels.worker;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Map {

    Graph<String, DefaultEdge> map = new SimpleGraph<>(DefaultEdge.class);
    ArrayList<String> packingAreas = new ArrayList<>();

    public void setGraph(JSONArray vertices, JSONArray edges) throws JSONException {

        for(int i=0; i<vertices.length();i++){
            map.addVertex(vertices.get(i).toString());
        }

        for(int i=0; i<edges.length();i++){
            map.addEdge(edges.get(i).toString().substring(1,5), edges.get(i).toString().substring(8,12));
        }
    }

    public String getMap(){
        return map.toString();
    }

    public List getShortestPath(String source, String destination){
        DijkstraShortestPath newPath = new DijkstraShortestPath(map);
        return newPath.getPath(source,destination).getVertexList();
    }

    public String getPackingArea() {
        return packingAreas.get(0);
    }

    public void setPackingAreas(JSONArray pa) throws JSONException {
        for(int i=0; i<pa.length();i++){
            packingAreas.add(pa.get(i).toString());
        }
        System.out.println("PackingAreas Created: "+packingAreas);
    }
}
