import java.util.ArrayList;
import java.util.List;

public class ModelCheckingOutput {

    List<List<Integer>> success_params;
    List<List<Integer>> factory_success_params;
    List<List<Integer>> remainder_success_params;
    List<List<Integer>> path_length_success_params;

    List<Float> inputs;


    ModelCheckingOutput(List<List<Integer>> success,
            List<List<Integer>> factory_success,
            List<List<Integer>> remainder_success,
            List<List<Integer>> path_length_success,
            List<Float> inputs_param){



        success_params = success;
        factory_success_params = factory_success;
        remainder_success_params = remainder_success;
        path_length_success_params = path_length_success;
        inputs = inputs_param;
    }
}
