package sky.courseproject.sockwarehouse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Socks {

    private Date date;

    private String typeOfAction;
    private String color;
    private double size;
    private int cottonPart;
    private int quantity;

    @Override
    public String toString() {
        return "Socks{" +
                "color='" + color + '\'' +
                ", size=" + size +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
