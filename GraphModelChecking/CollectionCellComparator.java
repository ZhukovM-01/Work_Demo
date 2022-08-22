import java.util.Comparator;

public class CollectionCellComparator implements Comparator<CollectionMatrixCell> {

    public int compare(CollectionMatrixCell cell1, CollectionMatrixCell cell2){
        return cell1.value.compareTo(cell2.value);
    }

}
