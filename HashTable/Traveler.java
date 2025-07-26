
class Traveler {
    String name;
    String surname;
    int budget;
    String ip;
    String time;
    String city;
    int price;
    int count;

    public Traveler(String name, String surname, int budget, String ip, String time, String city, int price, int count) {
        this.name = name;
        this.surname = surname;
        this.budget = budget;
        this.ip = ip;
        this.time = time;
        this.city = city;
        this.price = price;
        this.count = count;
    }

    void setCount(int count) {
        this.count = count;
    }
}
