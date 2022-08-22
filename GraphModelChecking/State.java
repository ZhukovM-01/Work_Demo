import java.util.List;

public class State {
    int state_index;
    Float factory_dist;
    List<DataNode> closest_nodes;
    Float capacity;
    List<DataNode> passed_nodes;
    Float total_length_passed;

    State(int ind_param, Float factory_dist_param, List<DataNode>closest_param,
            Float capacity_param, List<DataNode> passed_nodes_param, Float tot_len_param){
        state_index = ind_param;
        factory_dist = factory_dist_param;
        closest_nodes = closest_param;
        capacity = capacity_param;
        passed_nodes = passed_nodes_param;
        total_length_passed = tot_len_param;
    }

    @Override
    public String toString() {
        return "\n" + "State" + "\n" +
                "state_index=" + state_index + "\n" +
                "factory_dist=" + factory_dist + "\n" +
                "closest_nodes=" + closest_nodes + "\n" +
                "capacity=" + capacity + "\n" +
                "passed_nodes=" + passed_nodes + "\n";
    }
}
