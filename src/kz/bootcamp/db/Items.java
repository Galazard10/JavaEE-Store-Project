package kz.bootcamp.db;

public class Items {
    private long id;
    private String name;
    private String price;
    private String ram;
    private String memory;
    private String system;
    private String img;
    private int likes;

    public Items(){

    }

    public Items(long id, String name, String price, String ram, String memory, String system, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ram = ram;
        this.memory = memory;
        this.system = system;
        this.img = img;
    }

    public Items(String name, String price, String ram, String memory, String system, String img) {
        this.name = name;
        this.price = price;
        this.ram = ram;
        this.memory = memory;
        this.system = system;
        this.img = img;
    }

    public Items(long id, String name, String price, String ram, String memory, String system) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ram = ram;
        this.memory = memory;
        this.system = system;
    }

    public Items(String name, String price, String ram, String memory, String system) {
        this.name = name;
        this.price = price;
        this.ram = ram;
        this.memory = memory;
        this.system = system;
    }

    public Items(String name, String price, String ram, String memory, String system, String img, int likes) {
        this.name = name;
        this.price = price;
        this.ram = ram;
        this.memory = memory;
        this.system = system;
        this.img = img;
        this.likes = likes;
    }

    public Items(long id, String name, String price, String ram, String memory, String system, String img, int likes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ram = ram;
        this.memory = memory;
        this.system = system;
        this.img = img;
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes(){
        return likes;
    }
}