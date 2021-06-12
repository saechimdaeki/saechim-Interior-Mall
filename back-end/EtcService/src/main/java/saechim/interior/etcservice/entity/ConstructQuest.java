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
public class ConstructQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "construct_id")
    private Long id;

    private String title;

    private String questContent;


    @Embedded
    private UserInfo userInfo;

    @ElementCollection
    private List<String> strings=new ArrayList<>();

    @ElementCollection
    @Column(length = 16050379)
    private List<byte[]> questImage=new ArrayList<>(); /* 사진첨부는 세장만 하자 (프론트에서 처리) */

    @Builder
    public ConstructQuest(String title, String questContent,UserInfo userInfo) {
        this.title = title;
        this.questContent = questContent;
        this.userInfo=userInfo;
    }

    public void addImages(List<byte[]> questImages){
        this.questImage.addAll(questImages);
    }
}
