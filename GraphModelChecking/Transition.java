import java.util.ArrayList;
import java.util.List;


/// Transition class method algorithm() preforms 1 operation(edge)

/// chooseNode() assigns the endpoint of the edge



public class Transition {

    private static Integer local_node_index;
    static List<DataNode> local_possible_nodes;

    public static void algorithm(State state){

        state.state_index = chooseNode(state.capacity,state.closest_nodes);


        if (!local_node_index.equals(-1)) {
            state.capacity = state.capacity - local_possible_nodes.get(local_node_index-1).volume;
            state.passed_nodes.add(local_possible_nodes.get(local_node_index-1));
        }
        else{
            state.passed_nodes.add(new DataNode(-1, "factory", 0f, 64.49787f, 40.68435f));
        }




    }

    static Integer chooseNode(Float curr_capacity, List<DataNode> potential_nodes){

        local_possible_nodes = new ArrayList<>();

        local_node_index = 0;



        for (DataNode iterNode : potential_nodes){
            if (iterNode.volume < curr_capacity){
                local_possible_nodes.add(iterNode);
            }

        }

        Float max_volume = 0f;
        Integer counter = 0;
        for (DataNode iterNode : local_possible_nodes){
            counter++;
            if (iterNode.volume > max_volume){
                 max_volume = iterNode.volume;
                 local_node_index = counter;
            }
        }

        if (local_possible_nodes.size() == 0) {

            local_node_index = -1;
            return 0;
        }
        else {
            return local_possible_nodes.get(local_node_index-1).index;
        }
    }
}
