package com.backend.api.service;

import com.backend.api.request.CommentReq;
import com.backend.db.entity.BoardArticle;
import com.backend.db.entity.Comment;
import com.backend.db.repository.BoardRepository;
import com.backend.db.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalTime.now;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private BoardRepository boardRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository){
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
    }

    public List<Comment> getAllList(int articleSequence) {
        return commentRepository.findByArticleSequence(articleSequence);
    }

    @PrePersist // 데이터 생성이 이루어질때 사전 작업
    public void writeComment(CommentReq commentReq) {

        BoardArticle boardArticle = boardRepository.findById(commentReq.getArticleSequence()).get();

        Comment comment = Comment.builder()
                .userSequence(commentReq.getUserSequence())
                .boardArticle(boardArticle)
                .contents(commentReq.getContents())
                .goodCount(0)
                .open(0)
                .registerTime(String.valueOf(LocalDateTime.now())).build();
        commentRepository.save(comment);
    }

    public int deleteCommentAll(int noticeSequence) {
        
        return commentRepository.deleteByArticleSequence(noticeSequence);
    }

//    public Comment getOne(int articleSequence, int commentSequence) {
//        return commentRepository.findByArticleSequnceAndCommentSeqeunce(articleSequence,commentSequence);
//    }

    public void modify(Comment cur) {
        cur = Comment.builder().goodCount(cur.getGoodCount()+1).build();
        commentRepository.save(cur);
    }

    public int deleteComment(int commentSequence) {
        return commentRepository.deleteByCommentSequence(commentSequence);
    }
}
