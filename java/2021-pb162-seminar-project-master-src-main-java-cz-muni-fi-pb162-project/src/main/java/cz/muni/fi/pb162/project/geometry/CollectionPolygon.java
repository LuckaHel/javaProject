package cz.muni.fi.pb162.project.geometry;


import java.util.List;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/**
 * @author lucka helmeciova
 */
public class CollectionPolygon extends SimplePolygon{
    private  Vertex2D [] arrayVertices;
    private List<Vertex2D> listVertices;

    /**
     *
     * @param arrayVertices takes this param
     */
    public CollectionPolygon(Vertex2D [] arrayVertices) {
        super(arrayVertices);
        List<Vertex2D> listVertices= new ArrayList<Vertex2D>();
        for(Vertex2D lang:arrayVertices){
            listVertices.add(lang);
        }
        this.listVertices = listVertices;
    }

    /**
     *
     * @param myList takes list param
     */
    public CollectionPolygon(List<Vertex2D> myList){
        super(myList.toArray(new Vertex2D[0]));
        this.listVertices = List.copyOf(myList);

    }

    /**
     *
     * @param o object
     * @return - true if they ate equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        CollectionPolygon that = (CollectionPolygon) o;
        return Arrays.equals(arrayVertices, that.arrayVertices) && Objects.equals(listVertices, that.listVertices);
    }

    /**
     *
     * @return hash
     */

    @Override
    public int hashCode() {
        int result = Objects.hash(listVertices);
        result = 31 * result + Arrays.hashCode(arrayVertices);
        return result;
    }

    /**
     *
     * @return finds min x
     */

    public double minXValue(){
        double minX = listVertices.get(0).getX();
        for(int i = 0; i < listVertices.size(); i++){
            if(listVertices.get(i).getX()<minX){
                minX = listVertices.get(i).getX();
            }

        }
        return minX;
    }

    /**
     *
     * @param minX min x
     * @return list
     */
    public List myList(double minX){
        List myList = new ArrayList();
        for(int i = 0; i < listVertices.size(); i++){
            if(listVertices.get(i).getX() == minX){
                myList.add(listVertices.get(i));
            }
        }
        return myList;
    }
    /**
     *
     * @return deletes vertices on left side
     */
    public CollectionPolygon withoutLeftmostVertices(){
        if ( listVertices == null ){
            return null;
        }
        double minX = minXValue();
        List myList = myList(minX);
        if(listVertices.size()-myList.size() <= 0){
            return null;
        }
        Set<Vertex2D> hashSet = new HashSet<Vertex2D>();   // objekt na pridanie
        for (int i = 0; i < listVertices.size();i++){
            for (int j = 0; j < myList.size(); j++){
                if((!(listVertices.get(i).equals(myList.get(j))))){
                    hashSet.add(listVertices.get(i));
                }
            }
        }
        int counter = 0;
        Vertex2D[] result = new Vertex2D[listVertices.size()-myList.size()];
        for(int i = 0; i <listVertices.size();i++){
            if(hashSet.contains(listVertices.get(i))){
                result[counter] = listVertices.get(i);
                counter++;
            }
        }
        return new CollectionPolygon(result);
    }

    /**
     *
     * @param index vertex index, not negative number
     * @return vertex
     */
    @Override
    public Vertex2D getVertex(int index) {
        if(index == 6){
            return listVertices.get(0);
        }
        if (index < 0 ){
            throw new IllegalArgumentException("wrong index - ");
        }
        if (index >= listVertices.size()) {
            throw new IllegalArgumentException("wrong index + ");
        }


        return listVertices.get(index);
    }

    /**
     *
     * @return num of vertices
     */
    @Override
    public int getNumVertices() {
        return listVertices.size();
    }
}
