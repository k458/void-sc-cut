package com.k458.void_characters.model;

import lombok.Data;

import java.util.List;

@Data
public class CharactersDto {
    private List<CharacterEntity> characters;
}
