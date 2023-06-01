package cz.muni.fi.pb162.project.geometry;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Set;
import java.util.Map;
import java.util.Collection;
import java.util.Comparator;
import java.util.Collections;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;


/**
 * @author lucka helmeciova
 */
public final class LabeledPolygon extends SimplePolygon implements Labelable, Sortable, Polygon, PolygonWritable {
    private Map<String, Vertex2D> vertex2DMap = new TreeMap<>();

    /**
     * @param inputMap sorted map
     */
    private LabeledPolygon(Map<String, Vertex2D> inputMap) {
        super(inputMap.values().toArray(new Vertex2D[0]));
        vertex2DMap.putAll(inputMap);
    }

    /**
     * @param index vertex index, not negative number
     * @return vertex under given index
     */
    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }
        int myIndex = index % getNumVertices();
        int counter = -1;
        for (Map.Entry mapElement : vertex2DMap.entrySet()) {
            counter += 1;
            String key = (String) mapElement.getKey();
            if (myIndex == counter) {
                return vertex2DMap.get(key);
            }

        }
        return null;
    }

    /**
     * @return number of vertices
     */
    @Override
    public int getNumVertices() {
        return vertex2DMap.size();
    }

    /**
     * @param label label under which the vertex is stored
     * @return vertex under the given name
     */
    @Override
    public Vertex2D getVertex(String label) {
        if ((!vertex2DMap.containsKey(label))) {
            throw new NullPointerException("Not such vertex in map");
        }
        Vertex2D res = vertex2DMap.get(label);
        return res;
    }

    /**
     * @return all names of vertices
     */
    @Override
    public Collection<String> getLabels() {
        Collection<String> a = vertex2DMap.keySet();
        return Collections.unmodifiableCollection(a);
    }

    /**
     * @param vertex vertex which labels are searched
     * @return all labels
     */

    @Override
    public Collection<String> getLabels(Vertex2D vertex) {
        Collection<String> result = new HashSet<>();
        for (Map.Entry mapElement : vertex2DMap.entrySet()) {
            String key = (String) mapElement.getKey();
            if (vertex2DMap.get(key).equals(vertex)) {
                result.add(key);
            }
        }
        return result;
    }

    /**
     * @return
     */

    @Override
    public Collection<Vertex2D> getSortedVertices() {
        Object[] a = vertex2DMap.values().toArray();
        Set<Vertex2D> res = new TreeSet<>();
        for (Object vert : a) {
            res.add((Vertex2D) vert);
        }
        return res;

    }

    /**
     * @param comparator comparator object used to determine the ordering
     * @return sorted collcetion
     */

    @Override
    public Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator) {
        Set<Vertex2D> res = new TreeSet<>(comparator);
        res.addAll(vertex2DMap.values());
        return res;
    }

    /**
     * @param bw buffered writer
     * @throws IOException not writeable
     */
    public void mapParser(BufferedWriter bw) throws IOException {
        for (Map.Entry mapElement : vertex2DMap.entrySet()) {
            String key = (String) mapElement.getKey();
            String x = String.valueOf(vertex2DMap.get(key).getX());
            String y = String.valueOf(vertex2DMap.get(key).getY());
            String line = x + " " + y + " " + key;
            bw.write(line);
            bw.newLine();

        }
    }

    /**
     * @param os output stream
     * @throws IOException not writeable
     */
    @Override
    public void write(OutputStream os) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
            BufferedWriter bw = new BufferedWriter(writer);
            mapParser(bw);
            bw.flush();
        }
    }

    /**
     * @param file output file
     * @throws IOException not readable
     */

    @Override
    public void write(File file) throws IOException {
        try (OutputStream os = new FileOutputStream(file)) {
            OutputStreamWriter writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);
            mapParser(bw);
            bw.flush();
        }
    }

    /**
     * @author lucka helmeciova
     */
    public static class Builder implements Buildable<LabeledPolygon>, PolygonReadable {

        private Map<String, Vertex2D> vertex2DMapL = new TreeMap<>();

        /**
         * @return LabeledPolygon
         */
        @Override
        public LabeledPolygon build() {
            LabeledPolygon result = new LabeledPolygon(vertex2DMapL);
            return result;

        }

        /**
         * @param label name
         * @param vert  vertex
         * @return object
         * @throws IllegalArgumentException ,
         */
        public Builder addVertex(String label, Vertex2D vert) throws IllegalArgumentException {
            if (label == null) {
                throw new IllegalArgumentException("The label cannot be null");
            }
            if (vert == null) {
                throw new IllegalArgumentException("The vertex cannot be null");
            }
            vertex2DMapL.put(label, vert);
            return this;

        }

        /**
         *
         * @param strNum takes number in string format
         * @return true if it is number
         */
        public static boolean isNumeric(String strNum) {
            if (strNum == null) {
                return false;
            }
            try {
                double d = Double.parseDouble(strNum);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
        /**
         * @param br buffered reader
         * @throws IOException not readable
         */

        public void parser(BufferedReader br) throws IOException {
            while (br.ready()) {
                String line = br.readLine();
                String[] result = line.split(" ");
                if (!(isNumeric(result[0])& isNumeric(result[1]))){
                    throw new IOException();
                }
                double x = Double.parseDouble(result[0]);
                double y = Double.parseDouble(result[1]);
                String name = "";
                boolean noName = true;
                for (int i = 2; i < result.length; i++) {
                    noName = false;
                    if (i + 1 == result.length) {
                        name += result[i];
                    } else {
                        name += result[i] + " ";
                    }

                }
                if (noName){
                    throw new IOException();
                }
                addVertex(name, new Vertex2D(x, y));
            }
        }

        /**
         * @param is input stream
         * @return map of vertices
         * @throws IOException not readable
         */
        @Override
        public Builder read(InputStream is) throws IOException {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                parser(reader);
            }
            return this;
        }

        /**
         * @param file input file
         * @return Builder
         * @throws IOException not readable
         */

        @Override
        public Builder read(File file) throws IOException {
            try (InputStream is = new FileInputStream(file)) {
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(reader);
                parser(br);
            }
            return this;
        }
    }

    /**
     * @return duplicate vertices
     */
    public Collection<Vertex2D> duplicateVertices() {
        Collection<Vertex2D> result = new HashSet<>();
        Collection<Object> result1 = new HashSet<>();
        Object[] myList = vertex2DMap.values().toArray();
        for (Object o : myList) {
            if (result1.contains(o)) {
                result.add((Vertex2D) o);
            } else {
                result1.add(o);
            }
        }
        return result;
    }

    /**
     * @param os output stream
     * @throws IOException not readable
     */

    public void writeJson(OutputStream os) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(this.vertex2DMap);
        OutputStreamWriter writer = new OutputStreamWriter(os);
        writer.write(jsonOutput);
        writer.flush();
    }
}

