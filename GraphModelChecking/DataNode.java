public class DataNode {
    public int index;
    public String name;
    public Float volume;
    public Float x_coord;
    public Float y_coord;

    DataNode(int ind_param, String name_param, Float vol_param, Float x_param, Float y_param){
        index = ind_param;
        name = name_param;
        volume = vol_param;
        x_coord = x_param;
        y_coord = y_param;
    }

    public DataNode() {
    }

    @Override
    public String toString() {
        return  "\n"+
                "index=" + index
                + "\n"+
                "name='" + name + '\''
                +  "\n" +
                "volume=" + volume
                +"\n" +
                "x_coord=" + x_coord
                + "\n" +
                "y_coord=" + y_coord
                + "\n";
    }
}
