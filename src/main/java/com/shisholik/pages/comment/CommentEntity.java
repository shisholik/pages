package com.shisholik.pages.comment;

import com.shisholik.pages.BaseEntity;
import com.shisholik.pages.page.PageEntity;
import com.shisholik.pages.user.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class CommentEntity extends BaseEntity {
    private UserEntity creator;
    private String content;


    private PageEntity page;

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

    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false, insertable = true, updatable = true)
    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }
}
