package CoreJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyConstructor {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("GermenSheperd","Husky");
        Animal animal = new Animal("dog",list,1,2,4);
        Animal animal1= new Animal(animal);

        System.out.println(animal.getBody());
        System.out.println(animal1.getBody());

        animal1.getBody().setEyes(3);

        System.out.println(animal.getBody());
        System.out.println(animal1.getBody());
    }

}


class Animal{
    String name;
    List<String> varietyNames;
    int id;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    Body body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal(String name , List<String> varietyList,int id, int eyes, int legs){
        this.name=name;
        this.varietyNames=varietyList;
        this.id=id;
        this.body = new Body(eyes,legs);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getVarietyNames() {
        return varietyNames;
    }

    public void setVarietyNames(List<String> varietyNames) {
        this.varietyNames = varietyNames;
    }



    // shallow copy
//    public Animal(Animal animal){
//        this.id=animal.id;
//        this.name = animal.name;
//        this.varietyNames =animal.varietyNames;
//        this.body=animal.body;
//    }

    //deep copy
    public Animal(Animal animal){
        this.id=animal.id;
        this.name = animal.name;
        this.varietyNames =new ArrayList<>(animal.varietyNames);
        this.body=new Body(animal.getBody().getEyes(),animal.getBody().getLegs());
    }



    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", varietyNames=" + varietyNames +
                ", id=" + id +
                ", body=" + body +
                '}';
    }
}


class Body{
    int legs;

    public int getEyes() {
        return eyes;
    }

    public void setEyes(int eyes) {
        this.eyes = eyes;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    int eyes;


    public Body(int eyes , int legs){
        this.eyes=eyes;
        this.legs=legs;
    }

    @Override
    public String toString() {
        return "Body{" +
                "legs=" + legs +
                ", eyes=" + eyes +
                '}';
    }
}
