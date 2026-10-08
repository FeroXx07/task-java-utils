package level_1;

import java.io.Serializable;
import java.util.Objects;
public class Vehicle implements Serializable {
    private String model;
    private String color;
    private int numTyres;
    private int horsePower;

    public Vehicle(String model, String color, int numTyres, int horsePower) {
        this.model = model;
        this.color = color;
        this.numTyres = numTyres;
        this.horsePower = horsePower;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return numTyres == vehicle.numTyres && horsePower == vehicle.horsePower && Objects.equals(model, vehicle.model) && Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, color, numTyres, horsePower);
    }
}
