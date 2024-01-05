package com.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 2, message = "Title Should be at least 2 Characters")
    private String title;

    @NotEmpty
    @Size(min = 4, message = "Description Should be at least 4 Characters")
    private String description;

    @NotEmpty
    @Size(min = 4, message = "Content Should be at least 4 Characters")
    private String content;
}
