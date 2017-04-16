package dom4j_load_class;

/**
 * Created by oahnus on 2017/4/15
 * 20:57.
 */
public class CarFactoryA implements ICarFactory {

    @Override
    public void makeCar() {
        System.out.println("Factory A Make A Car");
    }
}
