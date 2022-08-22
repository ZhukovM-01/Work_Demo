import java.util.List;

public class FactoryNode extends DataNode {
    public Float maximum_capacity;
    public String name;

    public List<Float> transactions;


    public Float x_coord;
    public Float y_coord;

    FactoryNode(Float max_param, String name_param, List<Float>transactions_param,Float x_param, Float y_param){
        maximum_capacity = max_param;
        name = name_param;
        transactions = transactions_param;
        x_coord = x_param;
        y_coord = y_param;
    }
}
