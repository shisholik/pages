package com.shisholik.pages.page;

import com.shisholik.pages.BaseEntity;
import com.shisholik.pages.comment.CommentEntity;
import com.shisholik.pages.user.UserEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class PageEntity extends BaseEntity {
    private UserEntity creator;
    private String content;
    private Set<CommentEntity> comments = new HashSet<CommentEntity>(0);


    @OneToMany(mappedBy = "page", fetch = FetchType.LAZY)
    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    @OneToOne
    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
