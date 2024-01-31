package projectboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectboard.domain.*;
import projectboard.dto.post.CreatePostReqDto;
import projectboard.dto.post.UpdatePostDto;
import projectboard.exception.PostNotFoundException;
import projectboard.exception.TagNotFoundException;
import projectboard.exception.UserNotFoundException;
import projectboard.repository.PostMapper;
import projectboard.repository.PostTagMapper;
import projectboard.repository.TagMapper;
import projectboard.repository.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final UserMapper userMapper;
    private final TagMapper tagMapper;
    private final PostMapper postMapper;
    private final PostTagMapper postTagMapper;

    @Transactional
    @Override
    public void createPost(Long userId, CreatePostReqDto createPostReqDto) {

        Post post = Post.builder().
                userId(userId).
                title(createPostReqDto.getTitle()).
                content(createPostReqDto.getContent()).
                build();

        List<String> tagNames = createPostReqDto.getTagNames();

        if(userMapper.findUserById(post.getUserId())==null){
            throw new UserNotFoundException("해당 유저 없음");
        }
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
    public void updatePost(Long id, UpdatePostDto updatePostDto) {

        if(postMapper.findPostById(id)==null){
            throw new PostNotFoundException("해당 게시글을 찾을 수 없습니다.");
        }

        Post post = Post.builder().
                id(id).
                title(updatePostDto.getTitle()).
                content(updatePostDto.getContent()).
                build();

        postMapper.updatePost(post);
    }

    @Override
    public void deletePost(Long id) {
        if(postMapper.findPostById(id) == null){
            throw new PostNotFoundException("해당 게시글을 찾을 수 없습니다.");
        }
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
