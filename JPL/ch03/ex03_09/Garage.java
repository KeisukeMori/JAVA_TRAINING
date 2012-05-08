package ch03.ex03_09;

public class Garage implements Cloneable{
    private Vehicle[] buffer;
    private int garageNum;
    
    public Garage(int maxVehicle) {
        buffer = new Vehicle[maxVehicle];
        garageNum = -1;
    }
    
    public void push(Vehicle value) {
        ++garageNum;
        buffer[garageNum] = value;
    }
    
    public Vehicle[] getBuffer() {
    	return buffer;
    }
    
    @Override
    public Object clone() {
        try {
            Garage obj = (Garage) super.clone();
            obj.buffer = new Vehicle[buffer.length];
            for (int i = 0; i <= garageNum; i++) {
                obj.buffer[i] = (Vehicle) buffer[i].clone();
            }
            return obj;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }
        
    public static void main(String[] args) {
        Garage garage = new Garage(5);
        
        Vehicle car = new Vehicle("Mike");
        car.setVelocity(2);
        garage.push(car);
        
        Vehicle train = new Vehicle("Joe");
        train.setVelocity(4);
        garage.push(train);
        
        Garage newGarage = (Garage)garage.clone();
        
        for (int i = 0; i <= newGarage.garageNum; i++) {
            System.out.println(newGarage.buffer[i]);
        } 
    }
}
