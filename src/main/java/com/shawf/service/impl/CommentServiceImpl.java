package com.shawf.service.impl;

import com.shawf.dao.CommentDao;
import com.shawf.entity.Comment;
import com.shawf.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shawf_lee
 * @date 2020/7/2 15:28
 * Content:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> listComentsByBlogId(Long blogId) {
        Sort sort=Sort.by("createTime");
        List<Comment> commentList=commentDao.findByBlogIdAndParentCommentNull(blogId,sort);
        return loopComment(commentList);
    }



    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        if(parentCommentId!=-1){
            comment.setParentComment(commentDao.getOne(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        comment.setLastUpdateTime(new Date());
        return commentDao.save(comment);
    }

    /**
     * 环每个顶级的评论节点
     * @param commentList
     * @return
     */
    private List<Comment> loopComment(List<Comment> commentList) {
        List<Comment> commentList1=new ArrayList<>();
        for(Comment comment:commentList){
            Comment comment1=new Comment();
            BeanUtils.copyProperties(comment,comment1);
            commentList1.add(comment1);
        }
        //合并评论的各层子代到第一级子代集合中
        mergeChildComment(commentList1);
        return commentList1;
    }

    private void mergeChildComment(List<Comment> commentList) {
        for(Comment comment:commentList){
            List<Comment> replyList=comment.getSubCommentList();
            for(Comment reply:replyList){
                iterationReply(reply);
            }
            comment.setSubCommentList(tempComments);
            tempComments=new ArrayList<>();
        }
    }
    //存放迭代出的所有子代的集合
    private List<Comment> tempComments=new ArrayList<>();

    /**
     * 递归迭代
     * @param comment
     */
    private void iterationReply(Comment comment) {
        tempComments.add(comment);
        if(comment.getSubCommentList().size()>0){
            List<Comment> replyList=comment.getSubCommentList();
            for(Comment reply:replyList){
                tempComments.add(reply);
                if(reply.getSubCommentList().size()>0){
                    iterationReply(reply);
                }
            }
        }
    }



}
