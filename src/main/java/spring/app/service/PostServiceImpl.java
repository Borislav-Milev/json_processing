package spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.app.dao.PostRepository;
import spring.app.dao.UserRepository;
import spring.app.domain.Post;
import spring.app.exception.InvalidEntityDataException;
import spring.app.exception.NonExistingEntityException;
import spring.app.service.contract.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NonExistingEntityException(String.format("Post with ID %d does not exist.", id))
        );
    }

    @Transactional
    @Override
    public Post addPost(Post post) {
        post.setId(null);
        if(post.getAuthor() == null){
            if(post.getAuthorId() == null){
                throw new InvalidEntityDataException("Post author is required but missing!");
            }
            post.setAuthor(userRepository.findById(post.getAuthorId()).orElseThrow(
            () -> new InvalidEntityDataException(
                    String.format("Post author with ID: %s does not exist.", post.getAuthor()))));
        }
        return this.postRepository.save(post);
    }

    @Transactional
    @Override
    public Post updatePost(Post post) {
        getPostById(post.getId());
        return this.postRepository.save(post);
    }

    @Transactional
    @Override
    public Post deletePost(long id) {
        Post removed = getPostById(id);
        this.postRepository.deleteById(id);
        return removed;
    }

    @Override
    public long getPostsCount() {
        return this.postRepository.count();
    }
}
