package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 현재 서비스 코드의 문제점
    // 1. 추상화에 의존
    // 2. 구현체에 의존
    // 3. = DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
