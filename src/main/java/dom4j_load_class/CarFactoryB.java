package dom4j_load_class;

/**
 * Created by oahnus on 2017/4/15
 * 20:58.
 */
public class CarFactoryB implements ICarFactory {
    @Override
    public void makeCar() {
        System.out.println("Car Factory B Make A Car");
    }
}
