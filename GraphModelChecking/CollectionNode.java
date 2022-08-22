import java.util.ArrayList;
import java.util.List;


/// This class supports the the graph and its walk.

/// Method build() is the executive function, and
/// the model is unsatisfied for >10000 states

/// Method Algorithm() creates one transition operation.


public class CollectionNode {
    List<DataNode> local_data;
    FactoryNode local_factory;
    List<List<CollectionMatrixCell>> distance_matrix;
    List<CollectionMatrixCell> distance_factory;
    State state;

    Float capacity_init = 2000f;
    Integer node_number = 5;

    Integer satisfaction_flag = 0;
    Integer factory_counter = 0;
    Float remainder = 0f;
    Float path_length = 0f;

    int STATE_COUNTER;


    CollectionNode(List<DataNode> input_data, FactoryNode input_factory_data){
        local_data = input_data;

        remove_empty_data();
        remove_outliers();
        //System.out.printf("TOTAL NUMBER OF NODES %d", local_data.size());
        local_factory = input_factory_data;



        initialize_graph();


    }

    void build(){

        state = initialize_state();
        satisfaction_flag = 0;
        factory_counter = 0;

        STATE_COUNTER = 0;
        while (state.passed_nodes.size() < local_data.size()-1) {
            algorithm();
            STATE_COUNTER++;
            if (STATE_COUNTER > 10000) {
                satisfaction_flag = -1;
                break;
            };
        }
        if (state.passed_nodes.size() == local_data.size()-1){
            satisfaction_flag =1;
        }
        remainder = state.capacity;
        path_length = state.total_length_passed;


    }

    void algorithm(){

        int local_prev_index = state.state_index;

        Transition.algorithm(state);

        if (state.state_index != 0) {
             state.closest_nodes = get_closest_nodes(state);
        }
        else {
            state.factory_dist = 0f;
            state.closest_nodes = get_closest_factory(state);
            state.capacity = capacity_init;
            factory_counter++;

        }

        List<CollectionMatrixCell> current_node_vector = new ArrayList<>();
        for (List<CollectionMatrixCell> iterList : distance_matrix) {
            if (iterList.get(0).x_index == local_prev_index) {
                current_node_vector = iterList;
                break;
            }
        }

        Float temp_add = 0f;
        for (CollectionMatrixCell iterCell : current_node_vector) {
            if (iterCell.y_index == state.state_index) {
                temp_add = iterCell.value;
                break;
            }
        }

        state.total_length_passed = state.total_length_passed + temp_add;


    }

    void initialize_graph(){
        distance_matrix=build_distance_matrix();
        distance_factory = calculate_factory_distance();

    }
    State initialize_state(){
        List<DataNode> init_closest = new ArrayList<>();
        List<DataNode> init_passed = new ArrayList<>();
        State state =  new State(0,0f,
                init_closest,capacity_init,init_passed, 0f);
        state.closest_nodes = get_closest_factory(state);
        return state;
    }

    List<DataNode> get_closest_nodes(State current_state){
        List<DataNode> temp_closest_nodes = new ArrayList<>();
        int current_node_index = current_state.state_index;
        List<CollectionMatrixCell> current_node_vector = new ArrayList<>();
        for (List<CollectionMatrixCell> iterList : distance_matrix){
            if (iterList.get(0).x_index == current_node_index) {
                current_node_vector =iterList;
                break;
            }
        }
        current_node_vector.sort(new CollectionCellComparator());
        for (CollectionMatrixCell temp_cell : current_node_vector) {
            if (!current_state.passed_nodes.contains(find_node(temp_cell.y_index,local_data))) {
                temp_closest_nodes.add(find_node(temp_cell.y_index,local_data));
            }
            if (temp_closest_nodes.size() == node_number){
                return temp_closest_nodes;
            }
        }
        return temp_closest_nodes;
    }

    List<DataNode> get_closest_factory(State current_state){
        List<DataNode> temp_closest_nodes = new ArrayList<>();
        List<CollectionMatrixCell> temp_factory_dists = distance_factory;
        temp_factory_dists.sort(new CollectionCellComparator());
            for (CollectionMatrixCell temp_cell : temp_factory_dists) {
                if (!current_state.passed_nodes.contains(find_node(temp_cell.y_index,local_data))) {
                    temp_closest_nodes.add(find_node(temp_cell.y_index,local_data));
                }
                if (temp_closest_nodes.size() == node_number){
                    return temp_closest_nodes;
                }
            }
            return temp_closest_nodes;
    }


    List<List<CollectionMatrixCell>> build_distance_matrix() {

        List<List<CollectionMatrixCell>> matrix = new ArrayList<>();

        for (DataNode temp_xnode: local_data) {
            List<CollectionMatrixCell>column_vector = new ArrayList<>();
            for (DataNode temp_ynode : local_data){
                CollectionMatrixCell temp_cell = new CollectionMatrixCell(
                        temp_xnode.index, temp_ynode.index,euclidean_metric(temp_xnode,temp_ynode));
                column_vector.add(temp_cell);
            }
            matrix.add(column_vector);
        }
        return matrix;
    }

    List<CollectionMatrixCell> calculate_factory_distance(){

        List<CollectionMatrixCell>temp_distances = new ArrayList<>();
            for (DataNode local_datum : local_data) {
                temp_distances.add(
                        new CollectionMatrixCell(0, local_datum.index,
                                Float.parseFloat(String.valueOf(Math.sqrt(
                                        Math.pow((local_datum.x_coord - local_factory.x_coord), 2) +
                                                Math.pow((local_datum.y_coord - local_factory.y_coord), 2))))
                        )
                );
            }
        return temp_distances;
    }

    DataNode find_node(int dist_index, List<DataNode>dist_list){
        for (DataNode iter_node : dist_list){
            if (dist_index == iter_node.index){
                return iter_node;
            }
        }
        return new DataNode(-1,"DEBUG",-1f,-1f,-1f);
    }

    Float euclidean_metric(DataNode pointA, DataNode pointB){
        return Float.parseFloat(String.valueOf(Math.sqrt(
                                        Math.pow((pointA.x_coord-pointB.x_coord),2)+
                                                Math.pow((pointA.y_coord-pointB.y_coord),2))));
    }

    void remove_empty_data(){
        local_data.removeIf(iterNode -> iterNode.x_coord.equals(0f) ||
                iterNode.y_coord.equals(0f));

        for (int i = 1; i< local_data.size(); i++){
            local_data.get(i).index = i;
        }
    }

    void remove_outliers(){
        local_data.removeIf(iterNode-> iterNode.x_coord <= 39 ||
                                        iterNode.x_coord >= 41);
        local_data.removeIf(iterNode-> iterNode.y_coord <= 64 ||
                iterNode.x_coord >= 66);
    }
}
