package saechim.interior.etcservice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
/* 공지 사항*/
public class Notice {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private NoticeType type;

    @ElementCollection
    @Column(length = 16050379)
    private List<byte[]> postImages=new ArrayList<>();

    @Builder
    public Notice(String title, String content, NoticeType type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public void addImages(List<byte[]> images){
        this.postImages.addAll(images);
    }
}
