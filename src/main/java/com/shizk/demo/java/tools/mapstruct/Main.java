package com.shizk.demo.java.tools.mapstruct;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Morris", 5, CarType.SEDAN);
        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);
        System.out.println(car);
        System.out.println(carDto);
//        final HashMap<String, String> map = new HashMap<>();
//        map.put("make","Morris");
//        map.put("numberOfSeats","5");
//        map.put("type","SEDAN");
//        final Car car1 = CarMapper2.INSTANCE.mapToCar(map);
//        System.out.println(car1);
    }
}
