import java.util.ArrayList;
import java.util.List;

public class Service {

    private DataProvider dataProvider;
    private Object someObject;

    public Service(DataProvider dataProvider) {
        this(dataProvider, new Object());
    }

    public Service(DataProvider dataProvider, Object object) {
        this.dataProvider = dataProvider;
        this.someObject = object;
    }

    public List<String> getData() {
        List<String> result = new ArrayList<String>();
        String data = dataProvider.provideData();
        result.add(data);
        if(data.equals("data")) {
            result.add("atad");
        }
        someObject.hashCode();
        someObject.equals("anotherObjectHere");
        return result;
    }
}
