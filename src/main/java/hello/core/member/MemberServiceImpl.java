package hello.core.member;

/**
 * 설계 변경으로 인해 "MemberServiceImpl"은 "MemoryMemberRepository"를 의존하지 않는다.
 * 단지 "MemberRepository" 인터페이스만 의존한다 (=DIP 원칙 준수)
 * "MemberServiceImpl" 입장에서는 생성자를 통해 어떤 구현 객체가 들어올지(=주입될지)는 알 수 없다.
 * "MemberServiceImpl" 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(=AppConfig)에서 결정된다.
 * "MemberServiceImpl" 은 이제부터 "의존관계에 대한 고민은 외부" 맡기고 실행에만 집중 하면 된다.
 * DIP 완성 -> "MemberServiceImpl"은 "MemberRepository"인 추상에만 의존하면 된다.
 * 이제 구체 클래스를 몰라도 된다.
 * 관심사 분리 -> 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되어있다.
 */
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 의존관계 주입 = 의존성 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
