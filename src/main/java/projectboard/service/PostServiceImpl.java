package projectboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectboard.domain.*;
import projectboard.exception.MessageNotFoundException;
import projectboard.exception.PostNotFoundException;
import projectboard.exception.TagNotFoundException;
import projectboard.exception.UserNotFoundException;
import projectboard.repository.PostMapper;
import projectboard.repository.PostTagMapper;
import projectboard.repository.TagMapper;
import projectboard.repository.UserMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final UserMapper userMapper;
    private final TagMapper tagMapper;
    private final PostMapper postMapper;
    private final PostTagMapper postTagMapper;

    @Transactional
    @Override
    public void createPost(Post post, List<String> tagNames) {

        //게시글 생성
        postMapper.createPost(post);
        Long postId = post.getId();

            //해시태그가 있을땐 생성하지 않음.
            for (String tagName : tagNames) {
                if (tagMapper.findTagByName(tagName) == null) {
                    tagMapper.createTag(tagName);
                }
                PostTag postTag = PostTag.builder().
                        tagId(tagMapper.findTagByName(tagName).getId()).
                        postId(postId).
                        build();
                postTagMapper.createPostTag(postTag);
            }
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    @Override
    public void deletePost(Long id) {
        postMapper.deletePost(id);
    }

    @Override
    public Post findById(Long id) {
        return postMapper.findPostById(id);
    }

    @Override
    public List<Post> findByTitle(String title) {

        List<Post> posts = postMapper.findPostByTitle(title);
        if(posts.isEmpty()){
            throw new PostNotFoundException("게시글 조회 실패");
        }
        return posts;
    }

    @Override
    public List<Post> findByContent(String content) {
        List<Post> posts = postMapper.findPostByContent(content);
        if(posts.isEmpty()){
            throw new PostNotFoundException("게시글 조회 실패");
        }
        return posts;
    }

    @Override
    public List<Post> findByTagName(String tagName) {
        Tag tag = tagMapper.findTagByName(tagName);
        if(tag == null){
            throw new TagNotFoundException("해당 태그 없음.");
        }
        Long tagId = tag.getId();
        List<Post> posts = postMapper.findPostByTagId(tagId);
        if(posts.isEmpty()){
            throw new PostNotFoundException("게시글 조회 실패");
        }
        return posts;
    }

    @Override
    public List<Post> findByUserName(String userName) {
        User user = userMapper.findUserByUserName(userName);
        if(user==null){
            throw new UserNotFoundException("해당 유저 없음.");
        }

        Long userId = user.getId();
        List<Post> posts = postMapper.findPostByUserId(userId);
        if(posts.isEmpty()){
            throw new PostNotFoundException("게시글 조회 실패");
        }
        return posts;
    }
}
