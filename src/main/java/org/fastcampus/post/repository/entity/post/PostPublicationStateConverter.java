package org.fastcampus.post.repository.entity.post;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.fastcampus.post.domain.PostPublicationState;

@Converter
public class PostPublicationStateConverter implements AttributeConverter<PostPublicationState, String> {

    @Override
    public String convertToDatabaseColumn(PostPublicationState attribute) {
        return attribute.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String dbData) {
        return PostPublicationState.valueOf(dbData);
    }
}
