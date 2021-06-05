package saechim.interior.storyservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String title;

    private String userId;

    private String content;

    private Long postView=0L;

    @ElementCollection
    private final List<byte[]> postImages=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    @JsonManagedReference
    private List<Comment> comments=new ArrayList<>();

    @Builder
    public Post(Type type, String title, String userId, String content, Long postView) {
        this.type = type;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.postView = postView;
    }

    private void addViewCount(){
        this.postView++;
    }

    private void addImages(List<byte[]> images){
        this.postImages.addAll(images);
    }
}
