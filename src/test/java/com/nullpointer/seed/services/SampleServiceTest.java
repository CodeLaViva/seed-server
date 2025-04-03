package com.nullpointer.seed.services;

import com.nullpointer.seed.dto.response.SampleResponse;
import com.nullpointer.seed.entities.SampleEntity;
import com.nullpointer.seed.repositories.SampleRepository;
import com.nullpointer.seed.services.impls.SampleServiceImpl;
import com.nullpointer.seed.services.mappers.SampleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SampleServiceTest {

    @Mock
    private SampleRepository repository;

    @Spy
    private SampleMapper mapper = Mappers.getMapper(SampleMapper.class);

    @InjectMocks
    private SampleServiceImpl sampleService;

    @Test
    void should_get_sample_by_id() {
        int id = 1;
        SampleEntity entity = new SampleEntity();
        entity.setId(id);
        entity.setName("name");
        entity.setDescription("description");
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        SampleResponse actual = sampleService.get(id);

        assertNotNull(actual);
        assertEquals(entity.getId(), actual.getId());
        verify(repository).findById(id);
    }
}
