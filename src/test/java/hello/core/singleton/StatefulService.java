package hello.core.singleton;

public class StatefulService {

    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제?
    }

    public int getPrice() {
        return price;
    }

    /**
     * 그렇다면 어떻게 해결?
     * -> 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal을 사용
     */
//
//    public int order2(String name, int price) {
//        return price;
//    }
}
