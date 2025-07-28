package hello.core.member;

public interface MemberRepository {
    // Member 객체를 저장
    void save(Member member);

    // memberId로 회원 객체를 반환
    Member findById(Long memberId);
}
