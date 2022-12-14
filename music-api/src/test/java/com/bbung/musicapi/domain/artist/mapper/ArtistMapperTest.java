package com.bbung.musicapi.domain.artist.mapper;

import com.bbung.musicapi.domain.artist.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Sql("/truncate.sql")
class ArtistMapperTest {

    @Autowired
    private ArtistMapper artistMapper;

    private final int SUCCESS = 1;

    @Test
    @DisplayName("아티스트 등록 테스트")
    public void insertTest() throws Exception {

        ArtistFormDto artist = ArtistFormDto.builder()
                .name("뉴진스")
                .agency("ADOR")
                .birthday(LocalDate.of(2022, 7, 22))
                .contents("하이브 소속")
                .nationality("다국적")
                .registrant("bbung")
                .build();

        artistMapper.insert(artist);

        assertThat(artist.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("아티스트 상세보기 테스트")
    public void findTest() throws Exception {

        ArtistFormDto artist = ArtistFormDto.builder()
                .name("뉴진스")
                .agency("ADOR")
                .birthday(LocalDate.of(2022, 7, 22))
                .contents("하이브 소속")
                .nationality("다국적")
                .registrant("bbung")
                .build();

        artistMapper.insert(artist);

        ArtistDto artistDto = artistMapper.findById(artist.getId()).get();

        assertThat(artist.getName()).isEqualTo(artistDto.getName());
        assertThat(artist.getAgency()).isEqualTo(artistDto.getAgency());
        assertThat(artist.getBirthday()).isEqualTo(artistDto.getBirthday());
        assertThat(artist.getContents()).isEqualTo(artistDto.getContents());
        assertThat(artist.getNationality()).isEqualTo(artistDto.getNationality());
        assertThat(artist.getRegistrant()).isEqualTo(artistDto.getRegistrant());
    }

    @Test
    @DisplayName("아티스트 목록 테스트")
    public void findListTest() throws Exception {

        int loopCount = 100;

        for(int i = 0; i < loopCount; i++){
            ArtistFormDto artist = ArtistFormDto.builder()
                    .name("뉴진스" + i)
                    .agency("ADOR")
                    .birthday(LocalDate.of(2022, 7, 22))
                    .contents("하이브 소속")
                    .nationality("다국적")
                    .registrant("bbung")
                    .build();

            artistMapper.insert(artist);
        }

        ArtistSearchParam param = new ArtistSearchParam();
        param.setPageSize(loopCount);

        List<ArtistListDto> list = artistMapper.findList(param);

        assertThat(list.size()).isEqualTo(loopCount);
    }

    @Test
    @DisplayName("아티스트 수정 테스트")
    public void updateTest() throws Exception {

        ArtistFormDto artist = ArtistFormDto.builder()
                .name("뉴진스")
                .agency("ADOR")
                .birthday(LocalDate.of(2022, 7, 22))
                .contents("하이브 소속")
                .nationality("다국적")
                .registrant("bbung")
                .build();

        artistMapper.insert(artist);

        ArtistUpdateFormDto artistUpdateFormDto = new ArtistUpdateFormDto();
        artistUpdateFormDto.setName("뉴진스");
        artistUpdateFormDto.setAgency("하이브");
        artistUpdateFormDto.setBirthday(LocalDate.of(2022, 7, 22));
        artistUpdateFormDto.setContents("하이브 소속");
        artistUpdateFormDto.setNationality("다국적");

        int result = artistMapper.update(artist.getId(), artistUpdateFormDto);

        ArtistDto artistDto = artistMapper.findById(artist.getId()).get();

        assertThat(result).isEqualTo(SUCCESS);
        assertThat("하이브").isEqualTo(artistDto.getAgency());
    }

    @Test
    @DisplayName("아티스트 삭제 테스트")
    public void deleteTest() throws Exception {

        ArtistFormDto artist = ArtistFormDto.builder()
                .name("뉴진스")
                .agency("ADOR")
                .birthday(LocalDate.of(2022, 7, 22))
                .contents("하이브 소속")
                .nationality("다국적")
                .registrant("bbung")
                .build();

        artistMapper.insert(artist);

        int result = artistMapper.delete(artist.getId());

        assertThat(result).isEqualTo(SUCCESS);
    }
}