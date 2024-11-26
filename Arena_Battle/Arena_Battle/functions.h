#pragma once

#ifndef FUNCTIONS_H
#define FUNCTIONS_H

#include <iostream>
#include <vector>
#include <memory>
#include <SFML/Window.hpp>
#include <SFML/Graphics.hpp>
#include <SFML/Audio.hpp>
import gameMap;


void set_texture(sf::Texture& texture, const std::string& texture_name);

void set_music(sf::Music& music, const std::string& music_name);

//void set_center();

void EventHandling(sf::RenderWindow& window, sf::View& view, sf::Sprite& player, sf::Texture& texture, sf::Texture& texture2, sf::Sprite& arena, std::vector<std::unique_ptr<gameTile>>& obstacles, bool& battle);


//void collision(sf::Sprite& player, std::vector<std::unique_ptr<gameTile>>& obstacles);


#endif