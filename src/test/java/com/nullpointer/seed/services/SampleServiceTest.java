package com.nullpointer.seed.services;

import com.nullpointer.seed.dto.response.SampleResponse;
import com.nullpointer.seed.entities.SampleEntity;
import com.nullpointer.seed.repositories.SampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SampleServiceTest {

    @Mock
    private SampleRepository repository;

    @InjectMocks
    private SampleService sampleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // @Test
    void shouldGetSampleById() {
        // given
        int id = 1;
        SampleEntity entity = new SampleEntity();
        entity.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        // when
        SampleResponse actual = sampleService.get(id);

        // then
        assertNotNull(actual);
        assertEquals(entity.getId(), actual.getId());
        verify(repository).findById(id);
    }
}
