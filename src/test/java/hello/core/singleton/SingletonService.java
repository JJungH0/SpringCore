package hello.core.singleton;

public class SingletonService {

    /**
     * static 영역이란?
     * -> JVM(Java Virtual Machine)이 프로그램을 실행할 때, 클래스를 로딩하면서
     *    static 변수나 static 메서드를 메모리 상의 "static 영역"에 먼저 올려놓는 공간
     * -> 즉, 클래스 당 딱 1개씩 존재하는 공간
     */

    /**
     * JVM 메모리 구조 중 하나
     * Method Area(= static 영역) -> 클래스 정보, static 변수, static 메서드 저장
     * Heap -> 객체 인스턴스가 저장되는 공간
     * Stack -> 메서드 호출 시의 지역 변수, 파라미터 저장
     * PC Register -> 현재 실행 중인 명령어 주소 저장
     * Native Method Stack -> JVM이 아닌 네이티브 코드 호출용
     */

    private static final SingletonService instance = new SingletonService();

    private SingletonService() {
        // 객체 생성 내부적으로 막기
    }
    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
