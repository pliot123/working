package com.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_sequence")
    private Integer commentSequence;

    @ManyToOne
    @JoinColumn(name = "article_sequence")
    private BoardArticle boardArticle;
    @Column(name = "user_sequence")
    private Integer userSequence;
    private String title;
    private String contents;

    @Column(name = "good_count")
    private Integer goodCount;

    @Column(name = "open_close")
    private Integer open;
    private String registerTime;

}
