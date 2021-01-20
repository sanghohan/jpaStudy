package jpabook.japshop.domain;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//테이블과 관계없이 객체의 입장에서 상속기능만 사용하고싶을때 사용
//엔티티가 아님
//조회 검색 불가 em.find(BaseEntity....)
@MappedSuperclass
public class BaseEntity {

    private String createBy;
    private LocalDateTime createLocalDateTime;
    private LocalDateTime updateLocalDateTime;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateLocalDateTime() {
        return createLocalDateTime;
    }

    public void setCreateLocalDateTime(LocalDateTime createLocalDateTime) {
        this.createLocalDateTime = createLocalDateTime;
    }

    public LocalDateTime getUpdateLocalDateTime() {
        return updateLocalDateTime;
    }

    public void setUpdateLocalDateTime(LocalDateTime updateLocalDateTime) {
        this.updateLocalDateTime = updateLocalDateTime;
    }
}
