package lnp.movieclub.comment.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@NoArgsConstructor
public class CommentDto {
    private String userEmail;
    private String commentContent;
}
