package hello.core.member;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    // 동시성 문제 해결 -> ConcurrentHashMap
    // 내부적으로 락(Lock)을 사용하지만, 전체가 아닌 부분적으로 걸림 (Segment, Bucket 단위)

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
