package solid.humank.ddd.commons;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@FunctionalInterface
interface Converter {
    double convert(double input);
}

class LambdaDemo {
    double convert(Converter converter, double input) {

        return converter.convert(input);
    }
}

public class FuncInterfaceTest {

    @Test
    public void converterTest() {
        LambdaDemo sut = new LambdaDemo();

        // Convert Fahrenheit to Celsius
        System.out.println(sut.convert(input -> (input - 32) * 5.0 / 9.0, 98.6));
        // Convert Kilometers to Miles
        System.out.println(sut.convert(input -> input / 1.609344, 8));
    }

    @Test
    public void streamAPITest() throws FileNotFoundException {
        //BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"));
    }
}
