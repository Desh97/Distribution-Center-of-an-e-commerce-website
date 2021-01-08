package com.camels.simulator;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.Set;

public class Map {

    private int aisles = 0;
    private int sections = 0;
    private int shelves = 0;

    Graph<String, DefaultEdge> map = new SimpleGraph<>(DefaultEdge.class);
    ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    ArrayList<Vertex> packingAreas = new ArrayList<Vertex>();


    public void setMap(int aisles, int sections, int shelves, ArrayList packingAreas){

        this.aisles = aisles;
        this.sections = sections;
        this.shelves = shelves;

        createMap();

        for(int i=0;i<packingAreas.size();i++){
            String pa = packingAreas.get(i).toString();
            for(int v=0;v<vertices.size();v++){
                if(vertices.get(v).getName().equals(pa)){
                    Vertex tempVertex = vertices.get(v);
                    tempVertex.setAvailability(Boolean.FALSE, 0);
                    this.packingAreas.add(tempVertex);
                }
            }
        }

        System.out.println("check packing areas: "+this.packingAreas);

    }


    public void createMap(){
        for(int a=1; a<=aisles; a++){
            int s;
            for(s=0; s<=sections+1; s++){
                Vertex v = new Vertex("a"+a+"."+s, shelves);
                map.addVertex(v.getName());
                vertices.add(v);
                if(s==0){v.setAvailability(Boolean.FALSE, 0);}
                if(s>0){
                    map.addEdge("a"+a+"."+(s-1), "a"+a+"."+s);
                }
            }
            if(a>1){
                map.addEdge("a"+(a-1)+".0", "a"+a+".0");
                map.addEdge("a"+(a-1)+"."+(s-1), "a"+a+"."+(s-1));
            }
        }
        System.out.println("Map Created: "+map);
        //System.out.println("Vertices List: "+vertices.toString());
    }

    public String getMap(){
        return map.toString();
    }

    public Set<String> getVertices(){
        //System.out.println("Getting vertices: "+map.vertexSet());
        return map.vertexSet();
    }

    public ArrayList getVertexItems(String vertex){
        for(int i=0; i<vertices.size();i++){
            Vertex temp = vertices.get(i);
            if(temp.getName().equals(vertex)){
                return temp.getVertexItems();
            }
        }
        return null;
    }

    public Set<DefaultEdge> getEdges(){
        //System.out.println("Getting Edges: "+map.edgeSet());
        return map.edgeSet();
    }

    public ArrayList<String> getOpposites(String vertexId) {
        return (ArrayList<String>) Graphs.neighborListOf(map, vertexId);
    }

    public int getAisles() {
        return aisles;
    }

    public int getSections() {
        return sections;
    }

    public int getShelves() {
        return shelves;
    }

    public ArrayList<String> getPackingAreasArray() {
        ArrayList packingAreasArray = new ArrayList();
        for(Vertex pa: packingAreas){
            packingAreasArray.add(pa.getName());
        }
        return packingAreasArray;
    }

    public ArrayList<Vertex> getVerticesArray() {
        return vertices;
    }


}
