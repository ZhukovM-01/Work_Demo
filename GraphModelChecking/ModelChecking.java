import java.util.ArrayList;
import java.util.List;

public class ModelChecking {
    static List<Integer> capacity_array = new ArrayList<>();
    static List<Integer> node_num_array = new ArrayList<>();

    static void init(){
        for (int i = 500; i <= 4000; i = i + 500){
            capacity_array.add(i);
        }
        for (int i = 1; i < 20; i++){
            node_num_array.add(i);
        }
    }


    static boolean check_satisfaction(CollectionNode graph_param){
        return graph_param.satisfaction_flag == 1;
    }

    static boolean check_factory(CollectionNode graph_param, Integer factory_trip_num){
        return graph_param.factory_counter <= factory_trip_num;
    }

    static boolean check_remainder(CollectionNode graph_param, Float remainder_param){
        return graph_param.remainder <= remainder_param;
    }

    static boolean check_path_length(CollectionNode graph_param, Float length_param){
        return graph_param.path_length <= length_param;
    }



}
